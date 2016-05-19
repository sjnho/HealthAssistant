package com.sjn.healthassistant.pojo;

import io.realm.RealmObject;

/**
 * Created by sjn on 16/5/17.
 */
public class DrugAlarm extends RealmObject {


    private Drug drug;

    private long time;

    private int type;

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public Drug getDrug() {
        return drug;
    }

    public void setDrug(Drug drug) {
        this.drug = drug;
    }
}
