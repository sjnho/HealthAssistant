package com.sjn.healthassistant.pojo;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by sjn on 16/5/10.
 */
public class DrugLikeRecord extends RealmObject {
    @PrimaryKey
    private int id;
    private Drug drug;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Drug getDrug() {
        return drug;
    }

    public void setDrug(Drug drug) {
        this.drug = drug;
    }
}
