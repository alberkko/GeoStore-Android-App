package com.example.store;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface NodejsApi {

    @GET("stores")
    Call<ArrayList<ExampleItem>> getStores();


    @POST("stores")
    Call<ExampleItem> createStore(@Body ExampleItem item);
}
