package com.sjn.healthassistant.pojo;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by sjn on 16/5/1.
 */
public class DrugClassify extends RealmObject {
    /**
     * description : 性病用药,性病用药药品,性病用药相关药品,治疗性病用药相关功能的药品,性病用药相关药品查询,性病用药药品库
     * drugclass : 0
     * id : 1
     * keywords : 性病用药
     * name : 性病用药
     * seq : 0
     * title : 性病用药
     */
    @PrimaryKey
    private int id;
    private String description;
    private int drugclass;

    private String keywords;
    private String name;
    private int seq;
    private String title;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDrugclass() {
        return drugclass;
    }

    public void setDrugclass(int drugclass) {
        this.drugclass = drugclass;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
