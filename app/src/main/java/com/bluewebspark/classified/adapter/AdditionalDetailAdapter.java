package com.bluewebspark.classified.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bluewebspark.classified.R;
import com.bluewebspark.classified.Utility.Utility;
import com.bluewebspark.classified.activity.SubCategoryTab;
import com.bluewebspark.classified.model.AdditionalDetailModel;
import com.bluewebspark.classified.model.ProductDetailModel;

import java.util.List;

public class AdditionalDetailAdapter extends RecyclerView.Adapter<AdditionalDetailAdapter.MyViewHolder> {


    private List<AdditionalDetailModel> moviesList;
    Context cx;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvbrand, tvaccura;

        public MyViewHolder(View view) {
            super(view);
            tvbrand = (TextView) view.findViewById(R.id.tvbrand);
            tvaccura = (TextView) view.findViewById(R.id.tvaccura);

        }
    }


    public AdditionalDetailAdapter(Context cx, List<AdditionalDetailModel> moviesList) {
        this.cx = cx;
        this.moviesList = moviesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_additional_detail, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final AdditionalDetailModel additionalDetailModel = moviesList.get(position);
        holder.tvaccura.setText(additionalDetailModel.getValue());
        holder.tvbrand.setText(additionalDetailModel.getTitle());

/*
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                Utility.I(cx, SubCategoryTab.class, bundle);
            }
        });
*/


    }


    @Override

    public int getItemCount() {
        return moviesList.size();
    }

}


