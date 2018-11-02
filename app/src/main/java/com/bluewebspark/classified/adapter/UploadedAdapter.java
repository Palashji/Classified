package com.bluewebspark.classified.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bluewebspark.classified.R;
import com.bluewebspark.classified.Utility.S;
import com.bluewebspark.classified.Utility.Utility;
import com.bluewebspark.classified.activity.SubCategoryActivity;
import com.bluewebspark.classified.model.CityModel;
import com.bluewebspark.classified.model.UploadedModel;

import java.util.List;

import butterknife.BindView;


public class UploadedAdapter extends RecyclerView.Adapter<UploadedAdapter.MyViewHolder> {

    @BindView(R.id.imgprofile)
    ImageView imgprofile;
    private List<UploadedModel> moviesList;

    Context cx;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView imgprofile;

        public MyViewHolder(View view) {
            super(view);

            imgprofile = (ImageView) view.findViewById(R.id.imgprofile);

        }
    }

    public UploadedAdapter(Context cx, List<UploadedModel> moviesList) {
        this.cx = cx;
        this.moviesList = moviesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_uploaded, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final UploadedModel uploadedModel = moviesList.get(position);

        holder.imgprofile.setImageBitmap(uploadedModel.getImage());
        S.E("Palash " + uploadedModel.getImage());



    }

    @Override
    public int getItemCount() {
        return moviesList.size();

    }

}

