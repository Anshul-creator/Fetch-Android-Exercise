package com.example.fetchandroidexercise;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {

    private static Retrofit retrofit = null;

    public static Retrofit getRetrofit() {

        if (retrofit == null) {

            retrofit = new Retrofit.Builder()
                    .baseUrl("https://fetch-hiring.s3.amazonaws.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        }

        return retrofit;

    }

    public static ApiService getApiService() {
        return getRetrofit().create(ApiService.class);
    }

}