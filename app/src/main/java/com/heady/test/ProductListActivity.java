package com.heady.test;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

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

    TextView txt_productName ;

    LinearLayout layoutMain , layoutNodata ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);

        Intent intent = getIntent();
        categories = (JsonDataModel.Categories) intent.getSerializableExtra("categoryProducts");



        fetchIds();
    }

    private void fetchIds() {

        layoutMain = (LinearLayout) findViewById(R.id.layout_main);
        layoutNodata = (LinearLayout) findViewById(R.id.layout_nodata);

        txt_productName = (TextView) findViewById(R.id.txt_productName);

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

        if(categories.getProducts().size()==0){
            layoutMain.setVisibility(View.GONE);
            layoutNodata.setVisibility(View.VISIBLE);
        }


        productListAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(int position) {
        String productName = "";
        productName = categoriesList.get(0).getProducts().get(position).getName();
        if(productName!=null || !productName.equalsIgnoreCase("null") ||!productName.isEmpty()){
            txt_productName.setText(productName);
        }else {
            txt_productName.setText("Product Details");
        }
        variantsList.clear();
        for(int i=0 ; i < categoriesList.get(0).getProducts().get(position).getVariants().size();i++){
            variantsList.add(categoriesList.get(0).getProducts().get(position).getVariants().get(i));

        }
        productDetailsAdapter.notifyDataSetChanged();
    }
}
