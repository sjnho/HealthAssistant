package com.sjn.healthassistant.util;

import android.content.Context;

/**
 * Created by Yi on 15/12/28.
 */
public class ScreenUtil {
    public static int dp2px(Context context, float value) {
        return (int) (context.getResources().getDisplayMetrics().density*value+0.5);
    }
    public static int sp2px(Context context, float value) {
        return (int) (context.getResources().getDisplayMetrics().scaledDensity*value+0.5);
    }
    public static int getScreenWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }
    public static int getScreenHeight(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }
}
