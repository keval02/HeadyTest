package com.heady.test.apis;

import android.support.annotation.NonNull;

import com.heady.test.constant.ApiURLs;
import com.heady.test.constant.StringConverterFactory;

import java.io.File;
import java.net.URLConnection;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Keval on 2/2/2018.
 */

public class ServiceGenerator {

    public static AdminAPI getAPIClass() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiURLs.BASE_URL)
                .client(getRequestHeader())
                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(StringConverterFactory.create())
                .build();
        return retrofit.create(AdminAPI.class);
    }

    private static OkHttpClient getRequestHeader() {

        OkHttpClient httpClient = new OkHttpClient().newBuilder().connectTimeout(60, TimeUnit.SECONDS).readTimeout(60, TimeUnit.SECONDS).build();

        return httpClient;
    }
}
