package com.bluewebspark.classified.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.bluewebspark.classified.R;
import com.bluewebspark.classified.Utility.S;
import com.bluewebspark.classified.Utility.Utility;
import com.bluewebspark.classified.adapter.SubCategoryAdapter;
import com.bluewebspark.classified.adapter.TransactionAdapter;
import com.bluewebspark.classified.model.SubCategoryModel;
import com.bluewebspark.classified.model.TransactionModel;
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

public class TransactionActivity extends BaseActivity {


    @BindView(R.id.tool_bar)
    Toolbar toolBar;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private ArrayList<TransactionModel> arrayList = new ArrayList<>();


    @Override
    protected int getContentResId() {
        return R.layout.activity_transaction;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        setToolbarWithBackButton("Transaction Activity");

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        CallApiforTransaction();
    }

    private void CallApiforTransaction() {

        new JSONParser(TransactionActivity.this).parseRetrofitRequest(ApiClient.getClient().create(ApiInterface.class).getTransaction(
                // UserDataHelper.getInstance().getData().get(0).getUserId()
                "50"
        ), new Helper() {
            @Override
            public void backResponse(String response) {
                Utility.E("Transaction response : " + response);
                try {
                    JSONObject jsonObject = new JSONObject(response);

                    if (jsonObject.get("status").equals("200")) {
                        JSONArray jsonArray = jsonObject.getJSONArray("data");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject json = jsonArray.getJSONObject(i);
                            TransactionModel transactionModel = new TransactionModel();
                            transactionModel.setProduct_name(json.getString("product_name"));
                            transactionModel.setAmount(json.getString("amount"));
                            transactionModel.setFeatured(json.getString("featured"));
                            transactionModel.setUrgent(json.getString("urgent"));
                            transactionModel.setHighlight(json.getString("highlight"));
                            transactionModel.setTransaction_gatway(json.getString("transaction_gatway"));
                            transactionModel.setDate(json.getString("date"));
                            transactionModel.setStatus(json.getString("status"));
                            arrayList.add(transactionModel);
                        }

                        recyclerView.setLayoutManager(new LinearLayoutManager(TransactionActivity.this));
                        TransactionAdapter transactionAdapter = new TransactionAdapter(TransactionActivity.this, arrayList);
                        recyclerView.setAdapter(transactionAdapter);
                    } else {
                        S.T(TransactionActivity.this, jsonObject.getString("message"));

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });

    }


}

