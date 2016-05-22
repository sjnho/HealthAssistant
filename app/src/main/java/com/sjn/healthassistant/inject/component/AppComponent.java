package com.sjn.healthassistant.inject.component;


import android.content.SharedPreferences;

import com.sjn.healthassistant.common.ShowApi;
import com.sjn.healthassistant.inject.module.AppModule;
import com.sjn.healthassistant.common.SjnService;

import javax.inject.Singleton;

import dagger.Component;
import io.realm.Realm;

/**
 * Created by Yi on 15/12/28.
 */
@Singleton
@Component(modules = AppModule.class)
public interface  AppComponent {
    SjnService getService();
    SharedPreferences getSharedPreferences();
    ShowApi getShowApi();
}
