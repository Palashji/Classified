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
import com.bluewebspark.classified.Utility.Utility;
import com.bluewebspark.classified.activity.SubCategoryTab;
import com.bluewebspark.classified.model.ProductDetailModel;

import java.util.List;

public class ProductDetailAdapter extends RecyclerView.Adapter<ProductDetailAdapter.MyViewHolder> {


    private List<ProductDetailModel> moviesList;
    Context cx;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView imgprofile;
        TextView tvtext;

        public MyViewHolder(View view) {
            super(view);
            tvtext = (TextView) view.findViewById(R.id.tvtext);
            imgprofile = (ImageView) view.findViewById(R.id.imgprofile);

        }
    }


    public ProductDetailAdapter(Context cx, List<ProductDetailModel> moviesList) {
        this.cx = cx;
        this.moviesList = moviesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_product_detail, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final ProductDetailModel productDetailModel = moviesList.get(position);
        if (!productDetailModel.getImage().equals("")) {
            S.setImageByPicasoNormal(cx, productDetailModel.getImage(), holder.imgprofile, null);
        }
        holder.tvtext.setText(position + 1 + "/" + moviesList.size());
    }


    @Override

    public int getItemCount() {
        return moviesList.size();
    }

}


