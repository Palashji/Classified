package com.bluewebspark.classified.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.bluewebspark.classified.R;
import com.bluewebspark.classified.adapter.AllCategoriesAdapter;
import com.bluewebspark.classified.model.AllCategoriesModel;

import java.util.ArrayList;

public class AllCategoriesActivity extends BaseActivity {
    RecyclerView recycler_view;
    private ArrayList<AllCategoriesModel> arrayList = new ArrayList<>();

    @Override
    protected int getContentResId() {
        return R.layout.activity_allcategories;


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setToolbarWithBackButton("Category");

        recycler_view = (RecyclerView) findViewById(R.id.recycler_view);
        setData();
    }

    private void setData() {
        arrayList.add(new AllCategoriesModel(R.drawable.ic_smartphonecall, "Categories"));
        arrayList.add(new AllCategoriesModel(R.drawable.ic_smartphonecall, "Categories"));
        arrayList.add(new AllCategoriesModel(R.drawable.ic_smartphonecall, "Categories"));
        arrayList.add(new AllCategoriesModel(R.drawable.ic_smartphonecall, "Categories"));
        arrayList.add(new AllCategoriesModel(R.drawable.ic_smartphonecall, "Categories"));
        arrayList.add(new AllCategoriesModel(R.drawable.ic_smartphonecall, "Categories"));
        arrayList.add(new AllCategoriesModel(R.drawable.ic_smartphonecall, "Categories"));
        arrayList.add(new AllCategoriesModel(R.drawable.ic_smartphonecall, "Categories"));
        arrayList.add(new AllCategoriesModel(R.drawable.ic_smartphonecall, "Categories"));
        arrayList.add(new AllCategoriesModel(R.drawable.ic_smartphonecall, "Categories"));
        arrayList.add(new AllCategoriesModel(R.drawable.ic_smartphonecall, "Categories"));
        arrayList.add(new AllCategoriesModel(R.drawable.ic_smartphonecall, "Categories"));
        arrayList.add(new AllCategoriesModel(R.drawable.ic_smartphonecall, "Categories"));
        arrayList.add(new AllCategoriesModel(R.drawable.ic_smartphonecall, "Categories"));
        arrayList.add(new AllCategoriesModel(R.drawable.ic_smartphonecall, "Categories"));
        arrayList.add(new AllCategoriesModel(R.drawable.ic_smartphonecall, "Categories"));


        recycler_view.setLayoutManager(new LinearLayoutManager(this));
        AllCategoriesAdapter allCategoriesAdapter = new AllCategoriesAdapter(this, arrayList);
        recycler_view.setAdapter(allCategoriesAdapter);


    }
}