package com.heady.test.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.heady.test.R;
import com.heady.test.allinterface.CategoryListInterface;
import com.heady.test.model.JsonDataModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nivida6 on 02-02-2018.
 */

public class CategoryAdapter extends BaseAdapter {

    List<JsonDataModel.Categories> categoriesList = new ArrayList<>();
    Context context ;
    CategoryListInterface categoryListInterface ;
    LayoutInflater inflater = null;

    public CategoryAdapter(List<JsonDataModel.Categories> categoriesList, Context context , CategoryListInterface listInterface) {
        this.categoriesList = categoriesList;
        this.context = context;
        this.categoryListInterface = listInterface;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return categoriesList.size();
    }

    @Override
    public Object getItem(int position) {
        return categoriesList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View view = inflater.inflate(R.layout.layout_category_list, parent, false);

        Typeface myTypeface = Typeface.createFromAsset(context.getAssets(), "fonts/Raleway_Regular.ttf");
        TextView txtCategoryName = (TextView) view.findViewById(R.id.txt_categoryName);
        txtCategoryName.setTypeface(myTypeface);

        String categoryName = "";
        categoryName = categoriesList.get(position).getName();

        if(categoryName == null || categoryName.equalsIgnoreCase(null) || categoryName.equalsIgnoreCase("null") || categoryName.isEmpty()){
            txtCategoryName.setText("");
        }else {
            txtCategoryName.setText(categoriesList.get(position).getName());
        }


        txtCategoryName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                categoryListInterface.onClick(position);
            }
        });

        return view;
    }




}
