package com.bluewebspark.classified.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.bluewebspark.classified.R;
import com.bluewebspark.classified.Utility.UserAccount;
import com.bluewebspark.classified.Utility.Utility;
import com.bluewebspark.classified.data.datahelper.UserDataHelper;
import com.bluewebspark.classified.utils.ApiClient;
import com.bluewebspark.classified.utils.ApiInterface;
import com.bluewebspark.classified.utils.Helper;
import com.bluewebspark.classified.utils.JSONParser;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChangePasswordActivity extends BaseActivity {


    @BindView(R.id.tool_bar)
    Toolbar toolBar;
    @BindView(R.id.etoldPassword)
    EditText etoldPassword;
    @BindView(R.id.etnewPassword)
    EditText etnewPassword;
    @BindView(R.id.btnChngPsw)
    Button btnChngPsw;

    @Override
    protected int getContentResId() {
        return R.layout.activity_change_password;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setToolbarWithBackButton("Change Password");
        ButterKnife.bind(this);

        btnChngPsw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (UserAccount.isEmpty(etoldPassword, etnewPassword)) {
                    if (UserAccount.isPasswordValid(etnewPassword)) {

                    }
                    callApiForPasswordChange();

                } else Utility.T(ChangePasswordActivity.this, "Password didn't match");
                {

                }

            }
        });


    }


    private void callApiForPasswordChange() {
        new JSONParser(ChangePasswordActivity.this).parseRetrofitRequest(ApiClient.getClient().create(ApiInterface.class).change_password(
                UserDataHelper.getInstance().getData().get(0).getUserId(),

                etoldPassword.getText().toString(),
                etnewPassword.getText().toString()

                )
                , new Helper() {
                    @Override

                    public void backResponse(String response) {
                        Utility.E("Password Change response : " + response);
                        Utility.T(ChangePasswordActivity.this, "Change Password Successfully");

                        try {
                            JSONObject jsonObject = new JSONObject(response);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

}