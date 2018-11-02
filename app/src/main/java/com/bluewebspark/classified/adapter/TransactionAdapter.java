package com.bluewebspark.classified.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bluewebspark.classified.R;
import com.bluewebspark.classified.model.TransactionModel;

import java.util.List;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.MyViewHolder> {


    private List<TransactionModel> moviesList;

    Context cx;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvAdertise;
        public TextView tvSeller;
        public TextView tvBuyer;
        public TextView tvAmount;
        public TextView tvPremium;
        public TextView tvPayment;
        public TextView tvDate;
        public TextView tvStatus;
        public CardView itemCardView;

        public MyViewHolder(View view) {
            super(view);

            tvAdertise = (TextView) view.findViewById(R.id.tvAdertise);
            tvSeller = (TextView) view.findViewById(R.id.tvSeller);
            tvBuyer = (TextView) view.findViewById(R.id.tvBuyer);
            tvAmount = (TextView) view.findViewById(R.id.tvAmount);
            tvPremium = (TextView) view.findViewById(R.id.tvPremium);
            tvPayment = (TextView) view.findViewById(R.id.tvPayment);
            tvDate = (TextView) view.findViewById(R.id.tvDate);
            tvStatus = (TextView) view.findViewById(R.id.tvStatus);
            itemCardView = (CardView) view.findViewById(R.id.itemCardView);


        }
    }

    public TransactionAdapter(Context cx, List<TransactionModel> moviesList) {
        this.cx = cx;
        this.moviesList = moviesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_transaction, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final TransactionModel transactionModel = moviesList.get(position);
        holder.tvAdertise.setText(transactionModel.getProduct_name() + " (" + transactionModel.getHighlight() + ")");
        holder.tvSeller.setText(transactionModel.getAmount() + " >> " + transactionModel.getFeatured());
        //    holder.tvBuyer.setText(transactionModel.getFeatured());
        holder.tvAmount.setText(transactionModel.getUrgent() + " (" + transactionModel.getTransaction_gatway() + ")");
        //    holder.tvPremium.setText(transactionModel.getHighlight());
        //     holder.tvPayment.setText(transactionModel.getTransaction_gatway());
        holder.tvDate.setText(transactionModel.getDate());
        // holder.tvStatus.setText(transactionModel.getStatus());
        holder.tvStatus.setText(transactionModel.getStatus());

        //   holder.tvStatus.setText("Status :    " + transactionModel.getStatus());


/*
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("cat_id", cityModel.getCatID());
                bundle.putString("cat_name", cityModel.getCat_name());
                Utility.I(cx, SubCategoryActivity.class, bundle);
            }
        });
*/
    }

    @Override
    public int getItemCount() {
        return moviesList.size();

    }

}
