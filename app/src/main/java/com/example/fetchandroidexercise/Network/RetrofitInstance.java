package com.example.fetchandroidexercise.Network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Class for creating and providing a Retrofit instance
 * This instance is configured with the base URL for the API and uses Gson for JSON deserialization
 */
public class RetrofitInstance {

    private static Retrofit retrofit = null;

    /**
     * Returns the singleton Retrofit instance, creating it if necessary
     * @return A configured Retrofit instance
     */
    public static Retrofit getRetrofit() {

        if (retrofit == null) {

            retrofit = new Retrofit.Builder()
                    .baseUrl("https://fetch-hiring.s3.amazonaws.com/") // Base URL for API calls, as per the document provided
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        }

        return retrofit;

    }

    /**
     * Returns an implementation of the ApiService interface using the Retrofit instance
     * @return An ApiService object to perform network calls
     */
    public static ApiService getApiService() {
        return getRetrofit().create(ApiService.class);
    }

}