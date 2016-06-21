package com.sjn.healthassistant.util;

import com.sjn.healthassistant.SjnApplication;

/**
 * Created by sjn on 16/6/15.
 */
public class SpUtil {
    public static void putInt(String key ,int value){
        SjnApplication.getAppComponent().getSharedPreferences().edit().putInt(key,value).apply();
    }
    public static int getInt(String key,int defValue){
        return SjnApplication.getAppComponent().getSharedPreferences().getInt(key,defValue);
    }
}
