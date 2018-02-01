package com.heady.test.apis;

import com.heady.test.constant.ApiURLs;
import com.heady.test.model.JsonDataModel;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Keval on 2/2/2018.
 */

public interface AdminAPI {
    @GET(ApiURLs.SERVICE_URL)
    Call<JsonDataModel> JSON_DATA_MODEL_CALL();
}
