package com.example.datafetcher;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity.java";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://fetch-hiring.s3.amazonaws.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ItemApi itemApi = retrofit.create(ItemApi.class);

        Call<List<Item>> call = itemApi.getItems();

        call.enqueue(new Callback<List<Item>>() {
            @Override
            public void onResponse(Call<List<Item>> call, Response<List<Item>> response) {

                if(!response.isSuccessful()){
                    Log.d(TAG, String.valueOf(response.code()));
                    return;
                }

                List<Item> items = response.body();

                for(Item item : items)
                    Log.i(TAG, "ListId: " + item.getListId() + ", Name: " + item.getName());

            }

            @Override
            public void onFailure(Call<List<Item>> call, Throwable t) {
                Log.e(TAG, t.getMessage());
                t.printStackTrace();
            }
        });
    }
}