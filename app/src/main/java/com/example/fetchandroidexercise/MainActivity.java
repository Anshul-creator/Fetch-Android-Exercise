package com.example.fetchandroidexercise;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ItemsAdapter adapter;
    private List<ListItem> listItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        fetchData();

    }

    private void fetchData() {

        ApiService apiService = RetrofitInstance.getApiService();
        Call<List<Item>> call = apiService.getItems();

        call.enqueue(new Callback<List<Item>>() {

            @Override
            public void onResponse(Call<List<Item>> call, Response<List<Item>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Item> items = response.body();
                    processData(items);
                }
            }

            @Override
            public void onFailure(Call<List<Item>> call, Throwable t) {
                Log.e("MainActivity", "Failed to fetch data", t);
            }
        });

    }

    private void processData(List<Item> items) {

        List<Item> filteredItems = new ArrayList<>();
        for (Item item : items) {
            if (!TextUtils.isEmpty(item.getName())) {
                filteredItems.add(item);
            }
        }

        HashMap<Integer, List<Item>> groupedMap = new HashMap<>();
        for (Item item : filteredItems) {
            if (!groupedMap.containsKey(item.getListId())) {
                groupedMap.put(item.getListId(), new ArrayList<Item>());
            }
            groupedMap.get(item.getListId()).add(item);
        }

        listItems = new ArrayList<>();
        List<Integer> keys = new ArrayList<>(groupedMap.keySet());
        Collections.sort(keys);

        for (Integer key : keys) {

            listItems.add(new HeaderItem(key));

            List<Item> groupItems = groupedMap.get(key);
            Collections.sort(groupItems, new Comparator<Item>() {
                @Override
                public int compare(Item o1, Item o2) {
                    int num1 = o1.getId();
                    int num2 = o2.getId();
                    return Integer.compare(num1, num2);
                }
            });

            int counter = 1;
            for (Item item : groupItems) {
                listItems.add(new DataItem(item, counter));
                counter++;
            }

        }

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapter = new ItemsAdapter(listItems);
                recyclerView.setAdapter(adapter);
            }
        });

    }

}