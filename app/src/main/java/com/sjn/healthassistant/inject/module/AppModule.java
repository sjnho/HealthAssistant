package com.sjn.healthassistant.inject.module;

import android.content.Context;
import android.content.SharedPreferences;


import com.sjn.healthassistant.SjnApplication;
import com.sjn.healthassistant.common.SjnService;


import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
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
        Retrofit retrofit = new Retrofit.Builder()
                .client(new OkHttpClient())
                .baseUrl("http://www.tngou.net/api/")
                .addConverterFactory(GsonConverterFactory.create())
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











