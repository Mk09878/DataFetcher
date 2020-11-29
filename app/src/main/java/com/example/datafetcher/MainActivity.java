package com.example.datafetcher;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import com.example.datafetcher.adapters.CustomExpandableListAdapter;
import com.example.datafetcher.api.ItemApi;
import com.example.datafetcher.models.Item;
import com.example.datafetcher.utils.DataProcessing;
import com.example.datafetcher.viewmodels.ItemViewModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity.java";
    private ItemViewModel itemViewModel;
    private ExpandableListView expandableListView;
    private ExpandableListAdapter expandableListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        expandableListView = findViewById(R.id.expandableListView);
        itemViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(ItemViewModel.class);
        itemViewModel.getItems().observe(this, items -> {
            DataProcessing dataProcessing = new DataProcessing(items);
            expandableListAdapter = new CustomExpandableListAdapter(this, new ArrayList<>(dataProcessing.getGroupSet()), dataProcessing.getDataMap());
            expandableListView.setAdapter(expandableListAdapter);
            for(Item item: items)
                Log.i(TAG, "id: " + item.getId() + ", listid: " + item.getListId() + ", name: " + item.getName());
        });
    }
}