package com.example.datafetcher.utils;

import com.example.datafetcher.models.Item;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * Used for processing of the data obtained from API
 */
public class DataProcessing {
    private List<Item> items;
    private TreeMap<String, List<String>> dataMap;

    public DataProcessing(List<Item> items){
        this.items = items;
        dataMap = new TreeMap<>();
    }

    /**
     * Filters out items with name as "" or null
     * Groups items by listId and sorts the listId
     * Sorts the items in a listId by name
     * @return A sorted map with key as the listId and value as list of names for that listId
     */
    public TreeMap<String, List<String>> getDataMap(){
        for(Item item : items){
            if(item.getName() == null || item.getName().equals(""))
                continue;

            String listId = String.valueOf(item.getListId());
            if(dataMap.containsKey(listId)){
                List<String> tempList = dataMap.get(listId);
                tempList.add(item.getName());
                dataMap.put(listId, tempList);
            }
            else{
                dataMap.put(listId, new ArrayList<>(Collections.singletonList(item.getName())));
            }
        }
        sortLists();
        return dataMap;
    }

    /**
     * Uses a custom comparator to sort the names
     */
    private void sortLists(){
        for(Map.Entry<String, List<String>> entry : dataMap.entrySet()){
            List<String> tempList = entry.getValue();
            Collections.sort(tempList, (s1, s2) -> Integer.parseInt(s1.split(" ")[1]) - Integer.parseInt(s2.split(" ")[1]));
            dataMap.put(entry.getKey(), tempList);
        }
    }
}
