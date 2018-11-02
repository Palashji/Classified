package com.bluewebspark.classified.activity;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bluewebspark.classified.R;
import com.bluewebspark.classified.Utility.S;
import com.bluewebspark.classified.Utility.Utility;
import com.bluewebspark.classified.data.datahelper.UserDataHelper;
import com.bluewebspark.classified.data.model.UserDataModel;
import com.bluewebspark.classified.utils.ApiClient;
import com.bluewebspark.classified.utils.ApiInterface;
import com.bluewebspark.classified.utils.Helper;
import com.bluewebspark.classified.utils.JSONParser;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static android.Manifest.permission_group.CAMERA;

public class ProfileActivity extends BaseActivity {

    @BindView(R.id.tool_bar)
    Toolbar toolBar;
    @BindView(R.id.profile_image)
    CircleImageView profileImage;
    @BindView(R.id.tvname)
    TextView tvname;
    @BindView(R.id.tvlastlogging)
    TextView tvlastlogging;
    @BindView(R.id.tvmyaddigit)
    TextView tvmyaddigit;
    @BindView(R.id.tvmyadd)
    TextView tvmyadd;
    @BindView(R.id.tvmyfavroute)
    TextView tvmyfavroute;
    @BindView(R.id.tvmyfavrouitess)
    TextView tvmyfavrouitess;
    @BindView(R.id.tvmycoin)
    TextView tvmycoin;
    @BindView(R.id.tvcoins)
    TextView tvcoins;
    @BindView(R.id.tvdetail)
    TextView tvdetail;
    @BindView(R.id.etName)
    EditText etName;
    @BindView(R.id.etemail)
    EditText etemail;
    @BindView(R.id.etPhone)
    EditText etPhone;
    @BindView(R.id.etAddresss)
    EditText etAddresss;
    @BindView(R.id.tvsocialnetwork)
    TextView tvsocialnetwork;
    @BindView(R.id.etFacebook)
    EditText etFacebook;
    @BindView(R.id.etTwitter)
    EditText etTwitter;
    @BindView(R.id.etGoogle)
    EditText etGoogle;
    @BindView(R.id.etInstagram)
    EditText etInstagram;
    @BindView(R.id.etLinkedIn)
    EditText etLinkedIn;
    @BindView(R.id.etYoutube)
    EditText etYoutube;
    @BindView(R.id.btnupdate)
    Button btnupdate;
    private int TAKE_PICTURE = 101;
    private int RESULT_LOAD_IMAGE = 102;
    Bitmap bmp = null;


    @Override
    protected int getContentResId() {
        return R.layout.activity_profile;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        setToolbarWithBackButton("Profile");
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);


        etName.setText(UserDataHelper.getInstance().getData().get(0).getUserName());
        tvname.setText(UserDataHelper.getInstance().getData().get(0).getName());
        etemail.setText(UserDataHelper.getInstance().getData().get(0).getUserEmail());
        etPhone.setText(UserDataHelper.getInstance().getData().get(0).getPhone());
        etAddresss.setText(UserDataHelper.getInstance().getData().get(0).getAddress());
        etFacebook.setText(UserDataHelper.getInstance().getData().get(0).getFacebook());
        etTwitter.setText(UserDataHelper.getInstance().getData().get(0).getTwitter());
        etGoogle.setText(UserDataHelper.getInstance().getData().get(0).getGooglePlus());
        etInstagram.setText(UserDataHelper.getInstance().getData().get(0).getInstagram());
        etLinkedIn.setText(UserDataHelper.getInstance().getData().get(0).getLinkedin());
        etYoutube.setText(UserDataHelper.getInstance().getData().get(0).getYoutube());

        if (!UserDataHelper.getInstance().getData().get(0).getUserProfile().equals("")) {

            S.setImageByPicasoProfile(ProfileActivity.this, UserDataHelper.getInstance().getData().get(0).getUserProfile(), profileImage, null);
        }


        profileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkPermission()) {
                    openChooseImagePopup();
                } else {
                    requestPermission();

                }
            }
        });
        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDataUpdateProfile();
            }
        });

    }

    private void setDataUpdateProfile() {

        final String[] image = {""};
        final Bitmap[] imageBmp = {null};
        if (bmp != null) {
            image[0] = S.getEncoded64ImageStringFromBitmap(bmp);
        } else {
            try {
                Utility.E("profile:" + UserDataHelper.getInstance().getData().get(0).getUserProfile());
                final URL url = new URL(UserDataHelper.getInstance().getData().get(0).getUserProfile());
                Utility.E("hello:" + url);
                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        try {
                            imageBmp[0] = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        Utility.E("helloimage:" + imageBmp[0]);

                        image[0] = getBase64String(imageBmp[0]);
                    }
                }).start();


            } catch (IOException e) {
                System.out.println(e);
            }
        }

        S.E("image[0] : " + image[0]);
        S.E("name : " + etName.getText().toString());
        S.E("phone : " + etPhone.getText().toString());
        S.E("address : " + etAddresss.getText().toString());
        S.E("facebook : " + etFacebook.getText().toString());
        S.E("twitter : " + etTwitter.getText().toString());
        S.E("google : " + etGoogle.getText().toString());
        S.E("instagram : " + etInstagram.getText().toString());
        S.E("linkdin : " + etLinkedIn.getText().toString());
        S.E("youtube : " + etYoutube.getText().toString());
        S.E("id : " + UserDataHelper.getInstance().getData().get(0).getUserId());

        new JSONParser(ProfileActivity.this).parseRetrofitRequest(ApiClient.getClient().create(ApiInterface.class).updateprofile(
                UserDataHelper.getInstance().getData().get(0).getUserId(),
                image[0],
                etName.getText().toString(),
                etPhone.getText().toString(),
                etAddresss.getText().toString(),
                etFacebook.getText().toString(),
                etTwitter.getText().toString(),
                etGoogle.getText().toString(),
                etInstagram.getText().toString(),
                etLinkedIn.getText().toString(),
                etYoutube.getText().toString()
        ), new Helper() {
            @Override
            public void backResponse(String response) {
                Utility.E("update Profile :" + response);
                Utility.E(" Profile response : " + response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.get("status").equals("200")) {
                        JSONObject json = jsonObject.getJSONObject("data");
                        UserDataModel userDataModel = new UserDataModel();
                        userDataModel.setUserId(json.getString("id"));
                        userDataModel.setName(json.getString("name"));
                        userDataModel.setUserName(json.getString("username"));
                        userDataModel.setUserEmail(json.getString("email"));
                        userDataModel.setUserProfile(json.getString("image"));
                        userDataModel.setUserGroupID(json.getString("group_id"));
                        userDataModel.setPassword(json.getString("password_hash"));
                        userDataModel.setForgot(json.getString("forgot"));
                        userDataModel.setConfirm(json.getString("confirm"));
                        userDataModel.setStatus(json.getString("status"));
                        userDataModel.setViews(json.getString("view"));
                        userDataModel.setCreatedAt(json.getString("created_at"));
                        userDataModel.setUpdatedAt(json.getString("updated_at"));
                        userDataModel.setTagLine(json.getString("tagline"));
                        userDataModel.setDescription(json.getString("description"));
                        userDataModel.setSex(json.getString("sex"));
                        userDataModel.setPhone(json.getString("phone"));
                        userDataModel.setPostCode(json.getString("postcode"));
                        userDataModel.setAddress(json.getString("address"));
                        userDataModel.setCountry(json.getString("country"));
                        userDataModel.setCity(json.getString("city"));
                        userDataModel.setLastActive(json.getString("lastactive"));
                        userDataModel.setFacebook(json.getString("facebook"));
                        userDataModel.setTwitter(json.getString("twitter"));
                        userDataModel.setGooglePlus(json.getString("googleplus"));
                        userDataModel.setInstagram(json.getString("instagram"));
                        userDataModel.setLinkedin(json.getString("linkedin"));
                        userDataModel.setYoutube(json.getString("youtube"));
                        userDataModel.setOauth_Provider(json.getString("oauth_provider"));
                        userDataModel.setOauthUid(json.getString("oauth_uid"));
                        userDataModel.setOauthLink(json.getString("oauth_link"));
                        userDataModel.setOnline(json.getString("online"));
                        userDataModel.setNotify(json.getString("notify"));
                        userDataModel.setNotifyCat(json.getString("notify_cat"));
                        userDataModel.setCoins(json.getString("coins"));

                        UserDataHelper.getInstance().insertDataModel(userDataModel);

                        Utility.I_clear(ProfileActivity.this, MainActivity.class, null);

                    } else {

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }


    private void openChooseImagePopup() {
        final Dialog dialog = new Dialog(ProfileActivity.this);
        dialog.setContentView(R.layout.activity_profile_popup);

        Button btncancell = (Button) dialog.findViewById(R.id.btncancell);
        TextView tvTakePhoto = (TextView) dialog.findViewById(R.id.tvphoto);
        TextView tvChooseFromLibrary = (TextView) dialog.findViewById(R.id.tvlibrary);
        tvTakePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, TAKE_PICTURE);
            }
        });

        tvChooseFromLibrary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, RESULT_LOAD_IMAGE);
            }
        });

        btncancell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 101:
                if (grantResults.length > 0) {
                    boolean ACCESSSTORAGE = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    boolean ACCESSCAMERA = grantResults[1] == PackageManager.PERMISSION_GRANTED;

                    if (ACCESSSTORAGE && ACCESSCAMERA) {
                        openChooseImagePopup();
                    } else {
                        Toast.makeText(ProfileActivity.this, "Permission Denied", Toast.LENGTH_LONG).show();
                    }
                }
                break;
        }
    }

    //@RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void requestPermission() {
        ActivityCompat.requestPermissions(ProfileActivity.this, new String[]
                {
                        WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.CAMERA
                }, 101);
    }

    public boolean checkPermission() {
        int ThirdPermissionResult = ContextCompat.checkSelfPermission(ProfileActivity.this, WRITE_EXTERNAL_STORAGE);
        int FourthPermissionResult = ContextCompat.checkSelfPermission(ProfileActivity.this, CAMERA);
        return ThirdPermissionResult == PackageManager.PERMISSION_GRANTED &&
                FourthPermissionResult == PackageManager.PERMISSION_GRANTED;
    }


    private String getBase64String(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);

        byte[] imageBytes = baos.toByteArray();

        String base64String = Base64.encodeToString(imageBytes, Base64.NO_WRAP);

        return base64String;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == RESULT_LOAD_IMAGE && data != null) {
            Uri imgUri = data.getData();
            try {
                bmp = MediaStore.Images.Media.getBitmap
                        (getContentResolver(), imgUri);
            } catch (Exception ee) {
            }

            profileImage.setImageBitmap(bmp);

        }

        if (requestCode == TAKE_PICTURE && data != null) {
            Bundle bn = data.getExtras();
            bmp = (Bitmap) bn.get("data");
            profileImage.setImageBitmap(bmp);
        }
    }
}
