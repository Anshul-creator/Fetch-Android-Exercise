package com.example.fetchandroidexercise.Network;

import com.example.fetchandroidexercise.Model.Item;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Retrofit API interface defining the endpoints for network operations
 * Here, a GET request is defined to fetch a list of Item objects from the endpoint
 */
public interface ApiService {

    /**
     * Retrieves a list of items from the specified endpoint
     * The relative URL "hiring.json" will be appended to the base URL defined in RetrofitInstance
     * @return A Call object that yields a list of Item objects upon execution
     */
    @GET("hiring.json")
    Call<List<Item>> getItems();

}