package com.sjn.healthassistant.network;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by sjn on 16/4/19.
 */
public class SjnNetwork {
    private static volatile SjnService service;

    public static SjnService getService() {
        if (service == null) {
            synchronized (SjnNetwork.class) {
                if (service == null) {
                    Retrofit retrofit = new Retrofit.Builder()
                            .client(new OkHttpClient())
                            .baseUrl("http://www.tngou.net/api")
                            .addConverterFactory(GsonConverterFactory.create())
                            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                            .build();
                    service = retrofit.create(SjnService.class);
                }
            }
        }
        return service;
    }
}
