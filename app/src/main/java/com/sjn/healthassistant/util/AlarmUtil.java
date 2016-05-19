package com.sjn.healthassistant.util;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.sjn.healthassistant.common.Constants;
import com.sjn.healthassistant.pojo.Drug;
import com.sjn.healthassistant.widget.AlarmReceiver;

import java.util.Calendar;
import java.util.TimeZone;

/**
 * Created by sjn on 16/5/17.
 */
public class AlarmUtil {

    public static final int PER_DAY = 3600 * 24 * 1000;//一天

    public static void setRepeatingAlarm(Context context, int drugId, Calendar calendar, int interval) {
        Intent intent = new Intent("drug");
        intent.putExtra(Constants.EXTRA_DRUG_ID, drugId);
        intent.setClass(context, AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), interval, pendingIntent);

    }

    public static void setAlarm(Context context, int drugId, Calendar calendar) {
        Intent intent = new Intent("drug");
        intent.putExtra(Constants.EXTRA_DRUG_ID, drugId);
        intent.setClass(context, AlarmReceiver.class);
        intent.setClass(context, AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
    }

}
