package com.example.datafetcher;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;

import com.example.datafetcher.api.ItemApi;
import com.example.datafetcher.models.Item;
import com.example.datafetcher.viewmodels.ItemViewModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity.java";
    private ItemViewModel itemViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        itemViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(ItemViewModel.class);
        itemViewModel.getItems().observe(this, items -> {
            for(Item item: items)
                Log.i(TAG, "id: " + item.getId() + ", listid: " + item.getListId() + ", name: " + item.getName());
        });

    }
}