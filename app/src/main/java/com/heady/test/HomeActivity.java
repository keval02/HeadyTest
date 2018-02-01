package com.heady.test;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;
import com.heady.test.apis.AdminAPI;
import com.heady.test.apis.ServiceGenerator;
import com.heady.test.constant.AppPreference;
import com.heady.test.constant.Global;
import com.heady.test.helper.CustomProgressDialog;
import com.heady.test.model.JsonDataModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class HomeActivity extends AppCompatActivity {

    AdminAPI adminAPI;
    AppPreference appPreference ;
    CustomProgressDialog progressDialog ;

    List<JsonDataModel.Categories> categoriesList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        adminAPI = ServiceGenerator.getAPIClass();
        appPreference = new AppPreference(getApplicationContext());
        progressDialog = new CustomProgressDialog(HomeActivity.this);
        progressDialog.setCancelable(false);



        fetchIds();
    }

    private void fetchIds() {



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
                Log.e("response" , "->" + new Gson().toJson(dataModel));
                if(dataModel!=null){

                    categoriesList.addAll(dataModel.getCategories());

                    Log.e("listSize" , "->" + categoriesList.size());

                }else {
                    Global.defaultError(getApplicationContext());
                }
            }

            @Override
            public void onFailure(Call<JsonDataModel> call, Throwable t) {
                progressDialog.dismiss();
                Global.errorToast(getApplicationContext() , t);
            }
        });


    }


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
