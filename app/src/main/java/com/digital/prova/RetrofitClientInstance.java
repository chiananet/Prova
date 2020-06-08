package com.digital.prova;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientInstance {
    private static final String TAG = "RetrofitClientInstance";
    private static Retrofit retrofit;
    private static final String BASE_URL = "http://www.kidzinmind.com/it/static_env/lapis/apps/";

    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}