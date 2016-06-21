package com.sjn.healthassistant.util;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import com.sjn.healthassistant.common.Constants;
import com.sjn.healthassistant.pojo.Drug;
import com.sjn.healthassistant.ui.activity.AddAlarmActvivity;
import com.sjn.healthassistant.widget.AlarmReceiver;

import java.util.Calendar;
import java.util.TimeZone;

/**
 * Created by sjn on 16/5/17.
 */
public class AlarmUtil {

    public static final String ACTION = "com.sjn.drug";

    public static final int PER_DAY = 3600 * 24 * 1000;//一天

    /**
     * @param flag     0是一次性,1是循环
     * @param id       使用requestCode作为闹钟的的Id
     * @param context
     * @param message  提示信息
     * @param calendar 时间
     */

    public static void setAlarm(Context context, int flag, int id, String message, Calendar calendar) {
        int interval = flag == 0 ? 0 : PER_DAY;
        Intent intent = new Intent(ACTION);
        intent.putExtra(Constants.MESSAGE, message);
        intent.putExtra(Constants.REMIND_ID, id);
        intent.setClass(context, AlarmReceiver.class);
        PendingIntent sender = PendingIntent.getBroadcast(context, id, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            am.setWindow(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), interval, sender);
        } else {
            if (flag == 0) {
                am.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), sender);
            } else {
                am.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), interval, sender);
            }
        }
    }

    public static void cancelAlarm(Context context,String action,int id,String msg){
        Intent intent = new Intent(action);
        intent.putExtra(Constants.MESSAGE, msg);
        intent.putExtra(Constants.REMIND_ID, id);
        PendingIntent sender = PendingIntent.getBroadcast(context, id, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        am.cancel(sender);
    }


}
