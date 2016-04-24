package com.sjn.healthassistant;

import android.app.Application;
import android.content.Context;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.sjn.healthassistant.util.ImageLoadUtil;

/**
 * Created by sjn on 16/4/19.
 */
public class SjnApplication extends Application {




    @Override
    public void onCreate() {
        super.onCreate();
        ImageLoadUtil.init(getApplicationContext());
    }



}
