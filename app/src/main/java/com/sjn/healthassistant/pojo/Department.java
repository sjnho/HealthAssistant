package com.sjn.healthassistant.pojo;

import io.realm.RealmObject;

/**
 * Created by sjn on 16/5/19.
 */
public class Department extends RealmObject{

    /**
     * id : 1
     * name : 内科
     */

    private int id;
    private String name;

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
