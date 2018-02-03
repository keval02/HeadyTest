package com.heady.test;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.heady.test.adapter.CategoryAdapter;
import com.heady.test.allinterface.CategoryListInterface;
import com.heady.test.apis.AdminAPI;
import com.heady.test.apis.ServiceGenerator;
import com.heady.test.constant.Global;
import com.heady.test.helper.CustomProgressDialog;
import com.heady.test.model.JsonDataModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class HomeActivity extends AppCompatActivity implements CategoryListInterface {

    AdminAPI adminAPI;
    CustomProgressDialog progressDialog;

    List<JsonDataModel.Categories> categoriesList = new ArrayList<>();
    ListView listMainCategory;
    CategoryAdapter categoryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        adminAPI = ServiceGenerator.getAPIClass();
        progressDialog = new CustomProgressDialog(HomeActivity.this);
        progressDialog.setCancelable(false);


        fetchIds();
    }

    private void fetchIds() {

        listMainCategory = (ListView) findViewById(R.id.list_mainCategory);
        categoryAdapter = new CategoryAdapter(categoriesList, getApplicationContext(), this);
        listMainCategory.setAdapter(categoryAdapter); // Set Adapter To CategoryListInterface


        GetAllDatas();    //Function For Calling API
    }

    private void GetAllDatas() {
        progressDialog.show();
        Call<JsonDataModel> dataModelCall = adminAPI.JSON_DATA_MODEL_CALL();
        dataModelCall.enqueue(new Callback<JsonDataModel>() {
            @Override
            public void onResponse(Call<JsonDataModel> call, Response<JsonDataModel> response) {
                progressDialog.dismiss();
                JsonDataModel dataModel = response.body();
                if (dataModel != null) {
                    categoriesList.addAll(dataModel.getCategories());
                } else {
                    Global.defaultError(getApplicationContext());
                }
                categoryAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<JsonDataModel> call, Throwable t) {
                progressDialog.dismiss();
                Global.errorToast(getApplicationContext(), t);
            }
        });
    }


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    public void onClick(int position) {
        Intent intent = new Intent(getApplicationContext(), ProductListActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("categoryProducts", categoriesList.get(position));
        startActivity(intent);
    }

}
