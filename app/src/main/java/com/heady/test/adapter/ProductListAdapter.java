package com.heady.test.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.heady.test.R;
import com.heady.test.allinterface.CategoryListInterface;
import com.heady.test.model.JsonDataModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nivida6 on 02-02-2018.
 */

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.MyViewHolder> {

    List<JsonDataModel.Categories> categoriesList = new ArrayList<>();
    Context context ;
    CategoryListInterface categoryListInterface ;

    public ProductListAdapter(List<JsonDataModel.Categories> categoriesList, Context context , CategoryListInterface categoryListInterface) {
        this.categoriesList = categoriesList;
        this.context = context;
        this.categoryListInterface = categoryListInterface ;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_product_list, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        String productName = "";
        productName = categoriesList.get(0).getProducts().get(position).getName();
        holder.txtProductName.setText(productName);
        holder.txtProductName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                categoryListInterface.onClick(position);
            }
        });
        categoryListInterface.onClick(0);
    }

    @Override
    public int getItemCount() {
        return categoriesList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView txtProductName ;

        public MyViewHolder(View itemView) {
            super(itemView);

            txtProductName = (TextView) itemView.findViewById(R.id.txt_productName);

        }
    }

}
