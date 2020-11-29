package com.example.datafetcher;

import com.example.datafetcher.api.ItemApi;
import com.example.datafetcher.models.Item;
import com.example.datafetcher.utils.DataProcessing;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.internal.duplex.DuplexResponseBody;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DataProcessingTest {

    List<Item> items;

    public DataProcessingTest(){

        items = new ArrayList<>();

        items.add(new Item(4, 2, "Item 4"));
        items.add(new Item(6, 1, ""));
        items.add(new Item(3, 1, "Item 3"));
        items.add(new Item(9, 1, "Item 9"));
        items.add(new Item(14, 2, "Item 14"));
        items.add(new Item(10, 1, "Item 10"));
        items.add(new Item(18, 3, "Item 18"));
        items.add(new Item(2, 3, "Item 2"));
    }

    @Test
    public void test(){
        DataProcessing dataProcessing = new DataProcessing(items);
        Map<String, List<String>> dataMap = dataProcessing.getDataMap();

        List<String> groupTitlesOriginal = new ArrayList<>(dataMap.keySet());
        List<String> groupTitlesSorted = new ArrayList<>(dataMap.keySet());
        Collections.sort(groupTitlesSorted);

        assertEquals(groupTitlesSorted, groupTitlesOriginal);

        for(Map.Entry<String, List<String>> entry : dataMap.entrySet()){
            List<String> itemsOriginal = entry.getValue();
            List<String> itemsSorted = entry.getValue();
            Collections.sort(itemsSorted, (s1, s2) -> Integer.parseInt(s1.split(" ")[1]) - Integer.parseInt(s2.split(" ")[1]));

            assertEquals(itemsSorted, itemsOriginal);

        }

    }

}
