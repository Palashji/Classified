package com.bluewebspark.classified.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bluewebspark.classified.R;
import com.bluewebspark.classified.Utility.Utility;
import com.bluewebspark.classified.activity.SubCategoryActivity;
import com.bluewebspark.classified.activity.SubCategoryTab;
import com.bluewebspark.classified.model.CityModel;
import com.bluewebspark.classified.model.SubCategoryModel;

import java.util.List;

public class SubCategoryAdapter extends RecyclerView.Adapter<SubCategoryAdapter.MyViewHolder> {


    private List<SubCategoryModel> moviesList;
    Context cx;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public

        CardView itemCardView;
        TextView tvsubcateorytext;

        public MyViewHolder(View view) {
            super(view);
            tvsubcateorytext = (TextView) view.findViewById(R.id.tvsubcateorytext);
            itemCardView = (CardView) view.findViewById(R.id.itemCardView);

        }
    }


    public SubCategoryAdapter(Context cx, List<SubCategoryModel> moviesList) {
        this.cx = cx;
        this.moviesList = moviesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_sub_category, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final SubCategoryModel subCategoryModel = moviesList.get(position);

/*
        holder.tvsubcateorytext.setText(subCategoryModel.getSub_cat_name());
        holder.tvno.setText(  " ("+ subCategoryModel.getCount()+")");
*/

        holder.tvsubcateorytext.setText(subCategoryModel.getSub_cat_name() + "   (" + subCategoryModel.getCount() + ")");

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("sub_cat_id", subCategoryModel.getSub_cat_id());
                bundle.putString("cat_id", subCategoryModel.getMain_cat_id());
                bundle.putString("sub_cat_name", subCategoryModel.getSub_cat_name());
                Utility.I(cx, SubCategoryTab.class, bundle);
            }
        });


    }


    @Override

    public int getItemCount() {
        return moviesList.size();
    }

}


