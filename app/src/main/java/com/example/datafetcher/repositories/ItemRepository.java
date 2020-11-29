package com.example.datafetcher.repositories;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.datafetcher.api.ItemApi;
import com.example.datafetcher.models.Item;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
public class ItemRepository {

    private static final String TAG = "ItemRepository.java";
    private static final String API_BASE_URL = "https://fetch-hiring.s3.amazonaws.com/";
    private ItemApi itemApi;
    private final MutableLiveData<List<Item>> items;

    public ItemRepository(){
        itemApi = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ItemApi.class);

        items = new MutableLiveData<>();
    }

    public MutableLiveData<List<Item>> getItems(){
        itemApi.getItems().enqueue(new Callback<List<Item>>() {
            @Override
            public void onResponse(Call<List<Item>> call, Response<List<Item>> response) {
                if(!response.isSuccessful()){
                    Log.d(TAG, String.valueOf(response.code()));
                    return;
                }
                Log.i(TAG,"API call successful");

                items.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Item>> call, Throwable t) {
                Log.e(TAG, t.getMessage());
                t.printStackTrace();
            }
        });
        return items;
    }
}
