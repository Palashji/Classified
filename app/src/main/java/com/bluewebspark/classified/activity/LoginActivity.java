package com.bluewebspark.classified.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bluewebspark.classified.Utility.S;
import com.bluewebspark.classified.R;
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

public class LoginActivity extends BaseActivity {

    @BindView(R.id.imglogo)
    ImageView imglogo;
    @BindView(R.id.etEmail)
    EditText etEmail;
    @BindView(R.id.etPassword)
    EditText etPassword;
    @BindView(R.id.btnlogin)
    Button btnlogin;
    @BindView(R.id.layout)
    LinearLayout layout;
    @BindView(R.id.tvaccount)
    TextView tvaccount;
    @BindView(R.id.tvsingup)
    TextView tvsingup;

    @Override
    protected int getContentResId() {
        return R.layout.activity_login;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (UserAccount.isEmpty(etEmail, etPassword)) {
                    if (UserAccount.isEmailValid(etEmail)) {
                        if (UserAccount.isPasswordValid(etPassword)) {

                            callApiForLogin();
                        }

                    }
                }
            }
        });
    }

    private void callApiForLogin() {
        new JSONParser(this).parseRetrofitRequest(ApiClient.getClient().create(ApiInterface.class).user_login(
                etEmail.getText().toString(),
                etPassword.getText().toString()
        ), new Helper() {
            @Override
            public void backResponse(String response) {
                S.E("login response : " + response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.get("status").equals("200")) {
                        JSONObject json = jsonObject.getJSONObject("data");
                        UserDataModel userDataModel = new UserDataModel();
/*
                        userDataModel.setName(json.getString("username"));
                        userDataModel.setUserName(json.getString("password_hash"));
*/
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

                        Intent activityChangeIntent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(activityChangeIntent);
                        finish();
                    } else {
                        S.T(LoginActivity.this, jsonObject.getString("message"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}