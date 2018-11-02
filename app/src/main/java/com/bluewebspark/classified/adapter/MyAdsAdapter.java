package com.bluewebspark.classified.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bluewebspark.classified.R;
import com.bluewebspark.classified.Utility.S;
import com.bluewebspark.classified.Utility.Utility;
import com.bluewebspark.classified.model.MyAdsModel;
import com.bluewebspark.classified.utils.ApiClient;
import com.bluewebspark.classified.utils.ApiInterface;
import com.bluewebspark.classified.utils.Helper;
import com.bluewebspark.classified.utils.JSONParser;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class MyAdsAdapter extends RecyclerView.Adapter<MyAdsAdapter.MyViewHolder> {

    private List<MyAdsModel> moviesList;
    Context cx;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView adsImage;
        TextView tvAdTitle;
        TextView tvCategory;
        TextView tvloctaion;
        TextView tvTime;
        TextView tvCalender;
        TextView tvAmount;
        Button btnEdit;
        Button btnHide;
        Button btnDelete;
        CardView itemCardView;


        public MyViewHolder(View view) {
            super(view);
            adsImage = (ImageView) view.findViewById(R.id.adsImage);
            tvAdTitle = (TextView) view.findViewById(R.id.tvAdTitle);
            tvCategory = (TextView) view.findViewById(R.id.tvCategory);
            tvloctaion = (TextView) view.findViewById(R.id.tvloctaion);
            tvloctaion = (TextView) view.findViewById(R.id.tvloctaion);
            tvTime = (TextView) view.findViewById(R.id.tvTime);
            tvCalender = (TextView) view.findViewById(R.id.tvCalender);
            tvAmount = (TextView) view.findViewById(R.id.tvAmount);
            btnEdit = (Button) view.findViewById(R.id.btnEdit);
            btnHide = (Button) view.findViewById(R.id.btnHide);
            btnDelete = (Button) view.findViewById(R.id.btnDelete);
            itemCardView = (CardView) view.findViewById(R.id.itemCardView);

        }
    }


    public MyAdsAdapter(Context cx, List<MyAdsModel> moviesList) {
        this.cx = cx;
        this.moviesList = moviesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_my_ads, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final MyAdsModel myAdsModel = moviesList.get(position);
        holder.tvAdTitle.setText(myAdsModel.getProduct_name());
        holder.tvCategory.setText(myAdsModel.getSub_cat_name());
        holder.tvloctaion.setText(myAdsModel.getCity());
        holder.tvTime.setText(myAdsModel.getCreated_at());
        holder.tvCalender.setText(myAdsModel.getExpired_date());
/*
        holder.tvHide.setText(myAdsModel.getExpired_date());
        holder.tvEdit.setText(myAdsModel.getExpired_date());
        holder.tvDelete.setText(myAdsModel.getExpired_date());
*/


        S.setImageByPicasoNormal(cx, myAdsModel.getProduct_image(), holder.adsImage, null);



/*
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                S.I(cx, ProductDetailActivity.class, bundle);

            }
        });
*/
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callApifordelete(myAdsModel.getProID());


            }
        });

        holder.btnHide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callApiforHide(myAdsModel.getProID());
            }
        });
    }

    private void callApiforHide(String proID) {
        new JSONParser(cx).parseRetrofitRequest(ApiClient.getClient().create(ApiInterface.class).Hide(
                proID
                )
                , new Helper() {
                    @Override

                    public void backResponse(String response) {
                        Utility.E("Delete response : " + response);

                        try {
                            JSONObject jsonObject = new JSONObject(response);

                            if (jsonObject.getString("status").equals("200")) {
                                S.T(cx, jsonObject.getString("message"));

                                JSONObject jsonData = jsonObject.getJSONObject("hide_status");

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }


    private void callApifordelete(String proID) {
        new JSONParser(cx).parseRetrofitRequest(ApiClient.getClient().create(ApiInterface.class).Delete(
                proID)
                , new Helper() {
                    @Override

                    public void backResponse(String response) {
                        Utility.E("Delete response : " + response);

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    @Override

    public int getItemCount() {
        return moviesList.size();
    }


}
