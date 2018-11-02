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
import com.bluewebspark.classified.Utility.S;
import com.bluewebspark.classified.activity.ProductDetailActivity;
import com.bluewebspark.classified.model.PremiumModel;

import java.util.List;


public class PremiumAdapter extends RecyclerView.Adapter<PremiumAdapter.MyViewHolder> {


    private List<PremiumModel> moviesList;
    Context cx;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView adsImage;
        public TextView tvAdTitle, tvAdVCategory, tvloctaion, tvTime, tvAmount;

        public MyViewHolder(View view) {
            super(view);

            adsImage = (ImageView) view.findViewById(R.id.adsImage);
            tvAdTitle = (TextView) view.findViewById(R.id.tvAdTitle);
            tvAdVCategory = (TextView) view.findViewById(R.id.tvAdVCategory);
            tvloctaion = (TextView) view.findViewById(R.id.tvloctaion);
            tvTime = (TextView) view.findViewById(R.id.tvTime);
            tvAmount = (TextView) view.findViewById(R.id.tvAmount);
        }
    }

    public PremiumAdapter(Context cx, List<PremiumModel> moviesList) {
        this.cx = cx;
        this.moviesList = moviesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_ads, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final PremiumModel model = moviesList.get(position);
        holder.tvAdTitle.setText(model.getName());
        holder.tvAdVCategory.setText(model.getSubname());
        holder.tvloctaion.setText(model.getCity());
        holder.tvTime.setText(model.getTime());
        holder.tvAmount.setText(model.getBtnamount() + " Q");
        S.setImageByPicasoNormal(cx, model.getProfile(), holder.adsImage, null);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("proID", model.getId());
                S.I(cx, ProductDetailActivity.class, bundle);

            }
        });

    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }

}

