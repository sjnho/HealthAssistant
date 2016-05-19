package com.sjn.healthassistant.util;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import com.sjn.healthassistant.R;

/**
 * Created by sjn on 16/5/17.
 */
public class NotificationUtil {
    public static void showNotification(int msgId, Intent resultIntent, String title, String message, Context act) {
        // 创建一个即将要执行的PendingIntent对象
        PendingIntent resultPendingIntent = PendingIntent.getActivity(act, 0, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        Notification notification = new NotificationCompat.Builder(act)// 建立所要创建的Notification的配置信息，并有notifyBuilder来保存。
            .setAutoCancel(true)//触摸之后，通知立即消失
            .setWhen(System.currentTimeMillis())//显示的时间
            .setSmallIcon(R.mipmap.ic_launcher)//设置通知的小图标
            .setTicker("麋鹿")//设置状态栏显示的文本
            .setContentTitle(title)//设置通知的标题
            .setContentText(message)//设置通知的内容
            .setDefaults(Notification.DEFAULT_ALL)//设置（系统默认的）
            .setContentIntent(resultPendingIntent).build();//设置跳转的activity
        // 创建NotificationManager对象，并发布和管理所要创建的Notification
        NotificationManager mNotifyMgr = (NotificationManager) act.getSystemService(Context.NOTIFICATION_SERVICE);
        mNotifyMgr.notify(msgId, notification);
    }
}
