package com.bluewebspark.classified.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bluewebspark.classified.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HelpActivity extends BaseActivity {

    @BindView(R.id.tool_bar)
    Toolbar toolBar;
    @BindView(R.id.tvspeak)
    TextView tvspeak;
    @BindView(R.id.imgcall)
    ImageView imgcall;
    @BindView(R.id.tvor)
    TextView tvor;
    @BindView(R.id.tvqueries)
    TextView tvqueries;
    @BindView(R.id.etName)
    EditText etName;
    @BindView(R.id.etQuery)
    EditText etQuery;
    @BindView(R.id.btnsumbit)
    Button btnsumbit;

    @Override
    protected int getContentResId() {
        return R.layout.activity_help;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        setToolbarWithBackButton("Help Activity");
    }
}
