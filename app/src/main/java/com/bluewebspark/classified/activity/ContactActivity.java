package com.bluewebspark.classified.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bluewebspark.classified.R;
import com.bluewebspark.classified.Utility.S;
import com.bluewebspark.classified.Utility.Utility;
import com.bluewebspark.classified.utils.ApiClient;
import com.bluewebspark.classified.utils.ApiInterface;
import com.bluewebspark.classified.utils.Helper;
import com.bluewebspark.classified.utils.JSONParser;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ContactActivity extends BaseActivity {

    String user_id;
    @BindView(R.id.tool_bar)
    Toolbar toolBar;
    @BindView(R.id.imgprofile)
    ImageView imgprofile;
    @BindView(R.id.tvname)
    TextView tvname;
    @BindView(R.id.tvyear)
    TextView tvyear;
    @BindView(R.id.tvphone)
    TextView tvphone;
    @BindView(R.id.layout)
    LinearLayout layout;
    @BindView(R.id.tvlogin)
    TextView tvlogin;
    @BindView(R.id.tvemail)
    TextView tvemail;
    @BindView(R.id.login)
    LinearLayout login;
    @BindView(R.id.tvshare)
    TextView tvshare;
    @BindView(R.id.imgtwitter)
    ImageView imgtwitter;
    @BindView(R.id.imgfacebook)
    ImageView imgfacebook;
    @BindView(R.id.imgpintrest)
    ImageView imgpintrest;

    @Override
    protected int getContentResId() {
        return R.layout.activity_contact;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        setToolbarWithBackButton("Contact Activity");
        user_id = getIntent().getStringExtra("user_id");
        Utility.E("user_id : " + user_id);

        setDataContact();
    }

    private void setDataContact() {

        new JSONParser(this).parseRetrofitRequest(ApiClient.getClient().create(ApiInterface.class).productContactDetail(
                user_id

        ), new Helper() {
            @Override
            public void backResponse(String response) {
                S.E("product Contact response : " + response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.getString("status").equals("200")) {
                        S.T(ContactActivity.this, jsonObject.getString("message"));

                        JSONObject jsonData = jsonObject.getJSONObject("data");
                        // user_id = jsonData.getString("user_id");
                        String id = jsonData.getString("id");
                        String username = jsonData.getString("username");
                        String user_type = jsonData.getString("user_type");
                        String name = jsonData.getString("name");
                        String email = jsonData.getString("email");
                        String confirm = jsonData.getString("confirm");
                        String password = jsonData.getString("password");
                        String forgot = jsonData.getString("forgot");
                        String status = jsonData.getString("status");
                        String view = jsonData.getString("view");
                        String image = jsonData.getString("image");
                        String tagline = jsonData.getString("tagline");
                        String description = jsonData.getString("description");
                        String sex = jsonData.getString("sex");
                        String phone = jsonData.getString("phone");
                        String postcode = jsonData.getString("postcode");
                        String address = jsonData.getString("address");
                        String country = jsonData.getString("country");
                        String city = jsonData.getString("city");
                        String lastactive = jsonData.getString("lastactive");
                        String online = jsonData.getString("online");
                        String created_at = jsonData.getString("created_at");
                        String updated_at = jsonData.getString("updated_at");
                        String facebook = jsonData.getString("facebook");
                        String twitter = jsonData.getString("twitter");
                        String googleplus = jsonData.getString("googleplus");
                        String instagram = jsonData.getString("instagram");
                        String linkedin = jsonData.getString("linkedin");
                        String youtube = jsonData.getString("youtube");
                        String notify = jsonData.getString("notify");
                        String notify_cat = jsonData.getString("notify_cat");

                        S.setImageByPicasoProfile(ContactActivity.this, image, imgprofile, null);
                        //  S.setImageByPicaso(ContactActivity.this, "https://yak.com.gt/storage/profile/" + image, imgprofile, null);

                        tvname.setText(username);
                        tvyear.setText(created_at);
                        tvphone.setText(phone);


                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}


