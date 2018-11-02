package com.bluewebspark.classified.utils;


import retrofit2.Retrofit;

/**
 * Created by abc on 09-Mar-18.
 */

public class ApiClient {

    //   private static final String BASE_URL = "http://bwsproduction.com/volveGym/adminDashboard/index.php/api/";
    private static final String BASE_URL = "https://yak.com.gt/api/";

    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .build();
        }
        return retrofit;
    }
}