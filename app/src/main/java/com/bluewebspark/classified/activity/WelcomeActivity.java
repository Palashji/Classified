package com.bluewebspark.classified.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bluewebspark.classified.Utility.S;
import com.bluewebspark.classified.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WelcomeActivity extends BaseActivity {


    @BindView(R.id.imgyok)
    ImageView imgyok;
    @BindView(R.id.tvdescription)
    TextView tvdescription;
    @BindView(R.id.tvclassy)
    TextView tvclassy;
    @BindView(R.id.btnfacebook)
    Button btnfacebook;
    @BindView(R.id.btngogle)
    Button btngogle;
    @BindView(R.id.tvemail)
    TextView tvemail;
    @BindView(R.id.tvsignup)
    TextView tvsignup;
    @BindView(R.id.tvlogin)
    TextView tvlogin;
    @BindView(R.id.layout)
    LinearLayout layout;
    @BindView(R.id.tvtext)
    TextView tvtext;
    @BindView(R.id.tvtermscondition)
    TextView tvtermscondition;

    @Override
    protected int getContentResId() {
        return R.layout.activity_welcome;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        tvsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                S.I(WelcomeActivity.this, SignupActivity.class, null);

            }
        });
        tvlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                S.I(WelcomeActivity.this, LoginActivity.class, null);

            }
        });
    }
}
