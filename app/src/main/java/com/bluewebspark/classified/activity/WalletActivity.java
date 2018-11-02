package com.bluewebspark.classified.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.bluewebspark.classified.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WalletActivity extends BaseActivity {
    @BindView(R.id.tool_bar)
    Toolbar toolBar;
    @BindView(R.id.imgwhite)
    ImageView imgwhite;
    @BindView(R.id.tvwallet)
    TextView tvwallet;
    @BindView(R.id.tvdollar)
    TextView tvdollar;

    @Override
    protected int getContentResId() {
        return R.layout.activity_wallet;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        setToolbarWithBackButton("Wallet Amount");

    }
}
