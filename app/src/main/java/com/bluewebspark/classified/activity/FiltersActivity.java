package com.bluewebspark.classified.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bluewebspark.classified.Utility.S;
import com.bluewebspark.classified.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FiltersActivity extends BaseActivity {
    @BindView(R.id.tool_bar)
    Toolbar toolBar;
    @BindView(R.id.tvcategories)
    TextView tvcategories;
    @BindView(R.id.imgeyes)
    ImageView imgeyes;
    @BindView(R.id.tvallcategories)
    TextView tvallcategories;
    @BindView(R.id.imgarrow)
    ImageView imgarrow;
    @BindView(R.id.allcateorielayout)
    LinearLayout allcateorielayout;
    @BindView(R.id.viewing)
    View viewing;
    @BindView(R.id.etprice)
    EditText etprice;
    @BindView(R.id.etpriceto)
    EditText etpriceto;
    @BindView(R.id.views)
    View views;
    @BindView(R.id.tvsort)
    TextView tvsort;
    @BindView(R.id.tvdefault)
    TextView tvdefault;
    @BindView(R.id.imgico)
    ImageView imgico;
    @BindView(R.id.defaut)
    LinearLayout defaut;
    @BindView(R.id.btnapply)
    Button btnapply;

    @Override
    protected int getContentResId() {
        return R.layout.activity_filters;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        setToolbarWithBackButton("Filters");


        allcateorielayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                S.I(FiltersActivity.this, AllCategoriesActivity.class, null);

            }
        });
        defaut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                S.I(FiltersActivity.this, SortActivity.class, null);

            }
        });
    }
}
