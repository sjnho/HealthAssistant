package com.sjn.healthassistant.util;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sjn.healthassistant.pojo.serializer.DrugSerializer;
import com.sjn.healthassistant.pojo.serializer.HealthClassifySerializer;

import io.realm.DrugRealmProxy;
import io.realm.HealthClassifyRealmProxy;
import io.realm.RealmObject;

/**
 * Created by sjn on 16/5/4.
 */
public class RealmGson {
    public static Gson getGson() {
        if (mGson == null) {
            mGson = new GsonBuilder()
                .setExclusionStrategies(new ExclusionStrategy() {
                    @Override
                    public boolean shouldSkipField(FieldAttributes f) {
                        return f.getDeclaringClass().equals(RealmObject.class);
                    }

                    @Override
                    public boolean shouldSkipClass(Class<?> clazz) {
                        return false;
                    }
                })
                .registerTypeAdapter(HealthClassifyRealmProxy.class, new HealthClassifySerializer())
                .registerTypeAdapter(DrugRealmProxy.class,new DrugSerializer())
                .create();
        }
        return mGson;
    }

    private static volatile Gson mGson;
}
