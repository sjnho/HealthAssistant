package com.sjn.healthassistant.widget;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.sjn.healthassistant.common.Constants;
import com.sjn.healthassistant.pojo.Drug;
import com.sjn.healthassistant.util.NotificationUtil;

import io.realm.Realm;

/**
 * Created by sjn on 16/5/17.
 */
public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("drug")) {
            Drug drug = Realm.getDefaultInstance().where(Drug.class).equalTo("id", intent.getIntExtra(Constants.EXTRA_DRUG_ID, 0)).findFirst();
            if (drug != null) {
                NotificationUtil.showNotification(10001, new Intent(), "健康助手", "时间到了,该吃" + drug.getName(), context);
            }
        }
    }
}
