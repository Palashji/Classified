package com.bluewebspark.classified.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bluewebspark.classified.R;
import com.bluewebspark.classified.Utility.S;
import com.bluewebspark.classified.activity.ProductDetailActivity;
import com.bluewebspark.classified.model.FindAdsModel;

import java.util.List;

public class AdsAdapter extends RecyclerView.Adapter<AdsAdapter.MyViewHolder> {

    private List<FindAdsModel> moviesList;
    Context cx;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView tvcars;
        TextView carsbikes;
        TextView tvloctaion;
        TextView tvtime;
        TextView btnamount;
        CardView itemCardView;


        public MyViewHolder(View view) {
            super(view);
            img = (ImageView) view.findViewById(R.id.adsImage);
            tvcars = (TextView) view.findViewById(R.id.tvAdTitle);
            carsbikes = (TextView) view.findViewById(R.id.tvAdVCategory);
            tvloctaion = (TextView) view.findViewById(R.id.tvloctaion);
            tvtime = (TextView) view.findViewById(R.id.tvTime);
            btnamount = (TextView) view.findViewById(R.id.tvAmount);
            itemCardView = (CardView) view.findViewById(R.id.itemCardView);

        }
    }


    public AdsAdapter(Context cx, List<FindAdsModel> moviesList) {
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
        final FindAdsModel findAdsModel = moviesList.get(position);
//        holder.adsImage.setImageResource(findAdsModel.getProfile());
        holder.tvcars.setText(findAdsModel.getTextsell());
        holder.carsbikes.setText(findAdsModel.getTextbikesandcars());
        holder.tvloctaion.setText(findAdsModel.getLocation());
        holder.tvtime.setText(findAdsModel.getHours());
        holder.btnamount.setText(findAdsModel.getAmount() + " Q");


        S.setImageByPicasoNormal(cx, findAdsModel.getProfile(), holder.img, null);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("proID", findAdsModel.getProid());
                S.I(cx, ProductDetailActivity.class, bundle);

            }
        });
    }

    @Override

    public int getItemCount() {
        return moviesList.size();
    }


}
