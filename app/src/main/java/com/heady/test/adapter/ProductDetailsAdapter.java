package com.heady.test.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.heady.test.R;
import com.heady.test.model.JsonDataModel;
import com.heady.test.model.ProductDetailsModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nivida6 on 02-02-2018.
 */

public class ProductDetailsAdapter extends RecyclerView.Adapter<ProductDetailsAdapter.MyViewHolder> {

    List<ProductDetailsModel.Variants> variantsArrayList = new ArrayList<>();
    Context context;

    public ProductDetailsAdapter(List<ProductDetailsModel.Variants> variantsList, Context context) {
        this.variantsArrayList = variantsList;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_product_details, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        String color = "";
        String size = "";
        String price = "";

        color = variantsArrayList.get(position).getColor();
        size = variantsArrayList.get(position).getSize();
        price = variantsArrayList.get(position).getPrice();

        String nullable = null ;

        if (color.equals(nullable) ||color.isEmpty()) {
            holder.txtColor.setVisibility(View.GONE);
        } else {
            holder.txtColor.setVisibility(View.VISIBLE);
            holder.txtColor.setText("Color : " + color);
        }


        if (size.equals(nullable) || size.isEmpty()) {
            holder.txtSize.setVisibility(View.GONE);
        } else {
            holder.txtSize.setVisibility(View.VISIBLE);
            holder.txtSize.setText("Size : " + size);
        }


        if ( price.equals(nullable) || price.isEmpty()) {
            holder.txtPrice.setVisibility(View.GONE);
        } else {
            holder.txtPrice.setVisibility(View.VISIBLE);
            holder.txtPrice.setText("Price : " + price);
        }


    }

    @Override
    public int getItemCount() {
        return variantsArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView txtColor, txtSize, txtPrice;

        public MyViewHolder(View itemView) {
            super(itemView);

            txtColor = (TextView) itemView.findViewById(R.id.txt_color);
            txtSize = (TextView) itemView.findViewById(R.id.txt_size);
            txtPrice = (TextView) itemView.findViewById(R.id.txt_price);

        }
    }
}
