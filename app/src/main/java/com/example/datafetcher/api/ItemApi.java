package com.example.datafetcher.api;

import com.example.datafetcher.models.Item;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ItemApi {

    @GET("hiring.json")
    Call<List<Item>> getItems();
}
