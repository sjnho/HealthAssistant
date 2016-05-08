package com.sjn.healthassistant.inject.module;

import android.content.Context;
import android.content.SharedPreferences;


import com.sjn.healthassistant.SjnApplication;
import com.sjn.healthassistant.common.SjnService;
import com.sjn.healthassistant.util.RealmGson;


import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Yong on 15/12/28.
 */
@Module
public class AppModule {
    private SjnApplication sjnApplication;

    public AppModule(SjnApplication mApp) {
        this.sjnApplication = mApp;
    }

    @Provides
    @Singleton
    SjnApplication provideApp() {
        return sjnApplication;
    }

    @Provides
    @Singleton
    SjnService provideService() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
            .readTimeout(5, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(5, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor).build();

        Retrofit retrofit = new Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("http://www.tngou.net/api/")
            .addConverterFactory(GsonConverterFactory.create(RealmGson.getGson()))
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .build();
        return retrofit.create(SjnService.class);
    }

    @Provides
    @Singleton
    SharedPreferences provideSharedPreferences(SjnApplication mApp) {
        return mApp.getSharedPreferences(mApp.getPackageName(), Context.MODE_PRIVATE);
    }

}











