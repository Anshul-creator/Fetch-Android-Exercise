package com.example.fetchandroidexercise.UI;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fetchandroidexercise.Model.DataItem;
import com.example.fetchandroidexercise.Model.HeaderItem;
import com.example.fetchandroidexercise.Model.Item;
import com.example.fetchandroidexercise.Model.ListItem;
import com.example.fetchandroidexercise.Network.ApiService;
import com.example.fetchandroidexercise.Network.RetrofitInstance;
import com.example.fetchandroidexercise.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * MainActivity fetches JSON data using Retrofit, processes the items (filtering, grouping, and sorting),
 * and then displays them in a paginated RecyclerView
 * The list is built by grouping items based on their listId, inserting header items for each group,
 * and then paginating the combined list (headers and data items) using a fixed PAGE_SIZE of size 50
 */
public class MainActivity extends AppCompatActivity {

    // RecyclerView to display the items
    private RecyclerView recyclerView;
    // Adapter to bind the data to the RecyclerView
    private ItemsAdapter adapter;
    // List that holds the processed items (headers and data items) for display
    private List<ListItem> listItems;
    // The complete list of items (including headers) used for pagination
    private List<ListItem> completeListItems;
    // Current page index for pagination
    private int currentPage = 0;
    private final int PAGE_SIZE = 50;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize RecyclerView and set its layout manager
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Begin fetching data from the API
        fetchData();

        // Set up pagination buttons
        Button btnPrevious = findViewById(R.id.btnPrevious);
        Button btnNext = findViewById(R.id.btnNext);

        // Previous button: If not on the first page, decrement currentPage and load that page
        btnPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentPage > 0) {
                    currentPage--;
                    loadPage(currentPage);
                }
            }
        });

        // Next button: If not on the last page, increment currentPage and load next page
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int totalPages = (int) Math.ceil((double) completeListItems.size() / PAGE_SIZE);
                if (currentPage < totalPages - 1) {
                    currentPage++;
                    loadPage(currentPage);
                }
            }
        });

    }

    /**
     * Initiates a network request using Retrofit to fetch the list of items
     * Upon successful response it filters, groups, and sorts the items
     */
    private void fetchData() {

        ApiService apiService = RetrofitInstance.getApiService();
        Call<List<Item>> call = apiService.getItems();

        call.enqueue(new Callback<List<Item>>() {

            @Override
            public void onResponse(Call<List<Item>> call, Response<List<Item>> response) {
                // Only proceed if the response is successful and contains data
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

    /**
     * Processes the raw list of items:
     * 1. Filters out items with blank or null names
     * 2. Groups the remaining items by listId
     * 3. Sorts each group and adds a header for each group
     * 4. Combines headers and data items into a single list for pagination
     *
     * @param items: The raw list of items fetched from the API
     */
    private void processData(List<Item> items) {

        List<Item> filteredItems = new ArrayList<>();
        for (Item item : items) {
            // Filter items to exclude those with an empty name
            if (!TextUtils.isEmpty(item.getName())) {
                filteredItems.add(item);
            }
        }

        // Group the filtered items by listId
        HashMap<Integer, List<Item>> groupedMap = new HashMap<>();
        for (Item item : filteredItems) {
            if (!groupedMap.containsKey(item.getListId())) {
                groupedMap.put(item.getListId(), new ArrayList<Item>());
            }
            groupedMap.get(item.getListId()).add(item);
        }

        // Build a list that will contain header items and corresponding data items
        listItems = new ArrayList<>();
        List<Integer> keys = new ArrayList<>(groupedMap.keySet());
        Collections.sort(keys);

        // For each group, add a header and then add all sorted data items
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

        completeListItems = listItems;
        loadPage(currentPage);

    }

    /**
     * Loads a specific page from the complete list of items and updates the RecyclerView
     * @param page: The page index to load
     */
    private void loadPage(final int page) {

        // Calculate the start and end indices for the current page
        int startIndex = page * PAGE_SIZE;
        int endIndex = Math.min(startIndex + PAGE_SIZE, completeListItems.size());

        if (startIndex < endIndex) {

            final List<ListItem> pageItems = completeListItems.subList(startIndex, endIndex);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    adapter = new ItemsAdapter(pageItems);
                    recyclerView.setAdapter(adapter);
                }
            });

        }

    }

}