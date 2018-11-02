package com.bluewebspark.classified.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.bluewebspark.classified.R;
import com.bluewebspark.classified.model.ProductsTagModel;

import java.util.List;

public class ProductsTagsAdapter extends RecyclerView.Adapter<ProductsTagsAdapter.MyViewHolder> {


    private List<ProductsTagModel> moviesList;
    Context cx;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvProduct;

        public MyViewHolder(View view) {
            super(view);
            tvProduct = (TextView) view.findViewById(R.id.tvProduct);

        }
    }


    public ProductsTagsAdapter(Context cx, List<ProductsTagModel> moviesList) {
        this.cx = cx;
        this.moviesList = moviesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_products_tags, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final ProductsTagModel productsTagModel = moviesList.get(position);
        holder.tvProduct.setText(productsTagModel.getProduct());

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


