package com.bluewebspark.classified.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.bluewebspark.classified.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SortActivity extends BaseActivity {
    @BindView(R.id.tool_bar)
    Toolbar toolBar;
    @BindView(R.id.tvdefault)
    TextView tvdefault;
    @BindView(R.id.tvnewest)
    TextView tvnewest;
    @BindView(R.id.tvclosest)
    TextView tvclosest;
    @BindView(R.id.tvlowhigh)
    TextView tvlowhigh;
    @BindView(R.id.tvhighlow)
    TextView tvhighlow;

    @Override
    protected int getContentResId() {
        return R.layout.activity_sort;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        setToolbarWithBackButton("Sort By");

    }
}
