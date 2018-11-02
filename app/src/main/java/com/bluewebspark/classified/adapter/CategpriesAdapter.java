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

import java.util.List;


public class CategpriesAdapter extends RecyclerView.Adapter<CategpriesAdapter.MyViewHolder> {


    private List<CityModel> moviesList;

    Context cx;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView imgPicture;
        public TextView tvtext;
        public TextView tvNumber;

        public MyViewHolder(View view) {
            super(view);

            imgPicture = (ImageView) view.findViewById(R.id.tvLatest);
            tvtext = (TextView) view.findViewById(R.id.tvtext);
            tvNumber = (TextView) view.findViewById(R.id.tvNumber);
            Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
            CardView itemCardView = (CardView) view.findViewById(R.id.itemCardView);

        }
    }

    public CategpriesAdapter(Context cx, List<CityModel> moviesList) {
        this.cx = cx;
        this.moviesList = moviesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_city, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final CityModel cityModel = moviesList.get(position);
        holder.tvtext.setText(cityModel.getCat_name());
        holder.tvNumber.setText(  " ("+ cityModel.getCount()+")");
        //     holder.imgPicture.setImageResource(cityModel.getPicture());
        S.E("Palash " + cityModel.getCat_name());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("cat_id", cityModel.getCatID());
                bundle.putString("cat_name", cityModel.getCat_name());
                Utility.I(cx, SubCategoryActivity.class, bundle);
            }
        });

    }

    @Override
    public int getItemCount() {
        return moviesList.size();

    }

}

