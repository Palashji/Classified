package com.bluewebspark.classified.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.bluewebspark.classified.R;
import com.bluewebspark.classified.Utility.S;
import com.bluewebspark.classified.Utility.Utility;
import com.bluewebspark.classified.adapter.SubCategoryAdapter;
import com.bluewebspark.classified.model.SubCategoryModel;
import com.bluewebspark.classified.utils.ApiClient;
import com.bluewebspark.classified.utils.ApiInterface;
import com.bluewebspark.classified.utils.Helper;
import com.bluewebspark.classified.utils.JSONParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SubCategoryActivity extends BaseActivity {
    @BindView(R.id.tvtext)
    TextView tvtext;
    @BindView(R.id.ettext)
    EditText ettext;
    @BindView(R.id.tvtexting)
    TextView tvtexting;
    @BindView(R.id.btn)
    Button btn;
    @BindView(R.id.itemCardViewfull)
    CardView itemCardViewfull;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    String cat_id;

    private ArrayList<SubCategoryModel> arrayList = new ArrayList<>();


    @Override
    protected int getContentResId() {
        return R.layout.activity_sub_category;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ButterKnife.bind(this);

        setToolbarWithBackButton(getIntent().getStringExtra("cat_name"));

//        setToolbarWithBackButton("Sub Category");
        //   getSupportActionBar().setIcon(R.drawable.ic_magnifyingglass);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        cat_id = getIntent().getStringExtra("cat_id");
        Utility.E("cat_id : " + cat_id);
        callApiForSubCategories();
    }

    private void callApiForSubCategories() {

        new JSONParser(SubCategoryActivity.this).parseRetrofitRequest(ApiClient.getClient().create(ApiInterface.class).getSubCategories(
                cat_id
        ), new Helper() {
            @Override
            public void backResponse(String response) {
                Utility.E("sub category response : " + response);
                try {
                    JSONObject jsonObject = new JSONObject(response);

                    if (jsonObject.get("status").equals("200")) {
                        JSONArray jsonArray = jsonObject.getJSONArray("data");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject json = jsonArray.getJSONObject(i);
                            SubCategoryModel subCategoryModel = new SubCategoryModel();
                            subCategoryModel.setSub_cat_id(json.getString("sub_cat_id"));
                            subCategoryModel.setMain_cat_id(json.getString("main_cat_id"));
                            subCategoryModel.setSub_cat_name(json.getString("sub_cat_name"));
                            subCategoryModel.setSlug(json.getString("slug"));
                            subCategoryModel.setCat_order(json.getString("cat_order"));
                            subCategoryModel.setPhoto_show(json.getString("photo_show"));
                            subCategoryModel.setPrice_show(json.getString("price_show"));
                            subCategoryModel.setCount(json.getString("count"));
                            arrayList.add(subCategoryModel);
                        }

                        recyclerView.setLayoutManager(new LinearLayoutManager(SubCategoryActivity.this));
                        SubCategoryAdapter subCategoryAdapter = new SubCategoryAdapter(SubCategoryActivity.this, arrayList);
                        recyclerView.setAdapter(subCategoryAdapter);
                    } else {
                        S.T(SubCategoryActivity.this, jsonObject.getString("message"));

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.subcategory, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            case R.id.action_search:
                if (itemCardViewfull.getVisibility() == View.VISIBLE) {
                    itemCardViewfull.setVisibility(View.GONE);
                } else {
                    itemCardViewfull.setVisibility(View.VISIBLE);
                }
                break;

        }
        return true;
    }

}



