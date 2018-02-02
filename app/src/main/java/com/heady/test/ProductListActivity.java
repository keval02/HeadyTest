package com.heady.test;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.heady.test.adapter.ProductDetailsAdapter;
import com.heady.test.adapter.ProductListAdapter;
import com.heady.test.allinterface.CategoryListInterface;
import com.heady.test.model.JsonDataModel;
import com.heady.test.model.ProductDetailsModel;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ProductListActivity extends AppCompatActivity implements CategoryListInterface {

    JsonDataModel.Categories categories;

    RecyclerView recyclerProductList, recyclerProductDetails;
    ProductDetailsAdapter productDetailsAdapter;
    ProductListAdapter productListAdapter;

    List<JsonDataModel.Categories> categoriesList = new ArrayList<>();

    List<ProductDetailsModel.Variants> variantsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);

        Intent intent = getIntent();
        categories = (JsonDataModel.Categories) intent.getSerializableExtra("categoryProducts");



        fetchIds();
    }

    private void fetchIds() {

        recyclerProductList = (RecyclerView) findViewById(R.id.recyclerview_products);
        recyclerProductDetails = (RecyclerView) findViewById(R.id.recyclerview_productdetails);

        productListAdapter = new ProductListAdapter(categoriesList, getApplicationContext() , this);
        LinearLayoutManager gridLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL , false);
        recyclerProductList.setLayoutManager(gridLayoutManager);
        recyclerProductList.setItemAnimator(new DefaultItemAnimator());
        recyclerProductList.setAdapter(productListAdapter);


        productDetailsAdapter = new ProductDetailsAdapter(variantsList , getApplicationContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL , false);
        recyclerProductDetails.setLayoutManager(linearLayoutManager);
        recyclerProductDetails.setItemAnimator(new DefaultItemAnimator());
        recyclerProductDetails.setAdapter(productDetailsAdapter);


        GetAllProducts();
    }

    private void GetAllProducts() {
        categoriesList.clear();
        for(int i=0 ; i<categories.getProducts().size();i++){
            categoriesList.add(categories);
        }
        productListAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(int position) {
        variantsList.clear();
        for(int i=0 ; i < categoriesList.get(0).getProducts().get(position).getVariants().size();i++){
            variantsList.add(categoriesList.get(0).getProducts().get(position).getVariants().get(i));

        }
        productDetailsAdapter.notifyDataSetChanged();
    }
}
