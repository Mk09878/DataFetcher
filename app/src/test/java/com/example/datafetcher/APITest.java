package com.example.datafetcher;

import com.example.datafetcher.api.ItemApi;
import com.example.datafetcher.models.Item;

import org.junit.Test;
import static org.junit.Assert.*;

import java.io.IOException;
import java.util.List;

import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APITest {

    private static final String API_BASE_URL = "https://fetch-hiring.s3.amazonaws.com/";
    private ItemApi itemApi;

    @Test
    public void test() throws IOException {
        itemApi = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ItemApi.class);

        Response<List<Item>> response = itemApi.getItems().execute();
        assertTrue(response.isSuccessful());
        assertNotNull(response.body());
        assertNotNull(response.body().get(0));
    }
}
