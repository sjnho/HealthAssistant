package com.sjn.healthassistant.widget;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

import com.sjn.healthassistant.common.Constants;
import com.sjn.healthassistant.util.AlarmUtil;
import com.sjn.healthassistant.util.LogUtil;
import com.sjn.healthassistant.util.NotificationUtil;

/**
 * Created by sjn on 16/5/17.
 */
public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        LogUtil.d("进了Receiver,action:"+intent.getAction());
        if (intent.getAction().equals(AlarmUtil.ACTION)) {
            String msg = intent.getStringExtra(Constants.MESSAGE);
            LogUtil.d(msg);
            if (!TextUtils.isEmpty(msg)) {
                NotificationUtil.showNotification(10001, new Intent(), "健康助手", msg, context);
            }
        }
    }
}
