package com.bluewebspark.classified.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bluewebspark.classified.R;
import com.bluewebspark.classified.model.AllCategoriesModel;

import java.util.List;

public class AllCategoriesAdapter extends RecyclerView.Adapter<AllCategoriesAdapter.MyViewHolder> {
    private List<AllCategoriesModel> moviesList;
    Context cx;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvallcategories;
        public ImageView imgphone;

        public MyViewHolder(View view) {
            super(view);
            tvallcategories = (TextView) view.findViewById(R.id.tvallcategories);
            imgphone = (ImageView) view.findViewById(R.id.imgphone);

        }
    }

    public AllCategoriesAdapter(Context cx, List<AllCategoriesModel> moviesList) {
        this.cx = cx;
        this.moviesList = moviesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_allcategories, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        AllCategoriesModel allCategoriesModel = moviesList.get(position);
        holder.imgphone.setImageResource(allCategoriesModel.getProfile());
        holder.tvallcategories.setText(allCategoriesModel.getTotalpac());

    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }

}

