package com.example.datafetcher.viewmodels;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.datafetcher.models.Item;
import com.example.datafetcher.repositories.ItemRepository;

import java.util.List;

/**
 * ViewModel which retrieves the necessary data from the DataModel and then exposes relevant data for the View to consume
 */
public class ItemViewModel extends AndroidViewModel {

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
