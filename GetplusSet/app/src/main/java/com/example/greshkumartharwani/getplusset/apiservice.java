package com.example.greshkumartharwani.getplusset;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by Gresh Kumar Tharwani on 12/29/2017.
 */

public interface apiservice {

    @POST("posts")
    Call<model> postdata(@Body model obj);

    @GET("posts")
    Call<ArrayList<model>> getdata();
}
