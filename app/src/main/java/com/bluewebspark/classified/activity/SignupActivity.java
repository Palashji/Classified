package com.bluewebspark.classified.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bluewebspark.classified.R;
import com.bluewebspark.classified.Utility.S;
import com.bluewebspark.classified.Utility.UserAccount;
import com.bluewebspark.classified.data.datahelper.UserDataHelper;
import com.bluewebspark.classified.data.model.UserDataModel;
import com.bluewebspark.classified.utils.ApiClient;
import com.bluewebspark.classified.utils.ApiInterface;
import com.bluewebspark.classified.utils.Helper;
import com.bluewebspark.classified.utils.JSONParser;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SignupActivity extends BaseActivity {

    @BindView(R.id.imglogo)
    ImageView imglogo;
    @BindView(R.id.etname)
    EditText etname;
    @BindView(R.id.etemail)
    EditText etemail;
    @BindView(R.id.etmobileno)
    EditText etmobileno;
    @BindView(R.id.etpassword)
    EditText etpassword;
    @BindView(R.id.etconfirmpassword)
    EditText etconfirmpassword;
    @BindView(R.id.btnlogin)
    Button btnlogin;
    @BindView(R.id.layout)
    LinearLayout layout;

    @Override
    protected int getContentResId() {
        return R.layout.activity_signup;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (UserAccount.isEmpty(etname, etemail, etmobileno, etconfirmpassword, etpassword)) {
                    if (UserAccount.isEmailValid(etemail)) {
                        if (UserAccount.isPasswordValid(etconfirmpassword)) {
                            if (UserAccount.isPhoneLengthValid(etmobileno)) {

                                callApiForSignUp();
                            }
                        }
                    }
                }
            }
        });
    }

    private void callApiForSignUp() {
        new JSONParser(this).parseRetrofitRequest(ApiClient.getClient().create(ApiInterface.class).user_registration(
                etname.getText().toString(),
                etemail.getText().toString(),
                etconfirmpassword.getText().toString(),
                etname.getText().toString()
        ), new Helper() {
            @Override
            public void backResponse(String response) {
                S.E("registration response : " + response);
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


                        //   userDataModel.setFcm_id(json.getString("userfirbaseID"));

                        UserDataHelper.getInstance().insertDataModel(userDataModel);

                        Intent activityChangeIntent = new Intent(SignupActivity.this, MainActivity.class);
                        startActivity(activityChangeIntent);
                        finish();
                    } else {
                        S.T(SignupActivity.this, jsonObject.getString("message"));
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });

    }
}
