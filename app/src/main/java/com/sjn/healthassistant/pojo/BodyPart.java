package com.sjn.healthassistant.pojo;

import io.realm.RealmObject;

/**
 * Created by sjn on 16/6/13.
 */
public class BodyPart extends RealmObject {

    private String name;

    private int id;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
