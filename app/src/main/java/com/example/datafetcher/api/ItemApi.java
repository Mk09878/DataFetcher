package com.example.datafetcher.api;

import com.example.datafetcher.models.Item;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Interface whose methods actually fetch data
 * Concrete implementation of the methods is provided by retrofit
 */
public interface ItemApi {

    @GET("hiring.json")
    Call<List<Item>> getItems();
}
