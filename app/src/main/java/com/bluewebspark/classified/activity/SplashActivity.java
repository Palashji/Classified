package com.bluewebspark.classified.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.bluewebspark.classified.R;
import com.bluewebspark.classified.Utility.S;
import com.bluewebspark.classified.data.datahelper.UserDataHelper;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__splash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                S.E("data size : : : " + UserDataHelper.getInstance().getData().size());
                if (UserDataHelper.getInstance().getData().size() > 0) {
                    S.I_clear(SplashActivity.this, MainActivity.class, null);
                } else {
                    S.I_clear(SplashActivity.this, WelcomeActivity.class, null);
                }
            }
        }, 4000);
    }
}


