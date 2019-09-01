package com.vinaykumar.gamechangesns.Rest;

import com.google.gson.JsonArray;

import org.json.JSONArray;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by admin on 01-Sep-19.
 */

public interface ApiInterface {

    @GET("rest/v2/all")
    Call<JsonArray>  getCounrtyData();
}
