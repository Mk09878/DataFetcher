package com.example.datafetcher.viewmodels;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.datafetcher.models.Item;
import com.example.datafetcher.repositories.ItemRepository;

import java.util.LinkedHashMap;
import java.util.List;

public class ItemViewModel extends AndroidViewModel {

    LinkedHashMap<String, List<String>> itemsMap;

    private static final String TAG = "ItemViewModel.java";
    private LiveData<List<Item>> items;

    public ItemViewModel(@NonNull Application application) {
        super(application);
        ItemRepository itemRepository = new ItemRepository();
        items = itemRepository.getItems();
        Log.i(TAG, String.valueOf(items.getValue()));

    }

    public LiveData<List<Item>> getItems(){
        return items;
    }

}
