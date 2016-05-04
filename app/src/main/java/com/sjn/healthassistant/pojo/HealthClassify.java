package com.sjn.healthassistant.pojo;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by sjn on 16/5/1.
 */
public class HealthClassify extends RealmObject {
    /**
     * description : 减肥瘦身,美丽一生,减肥资讯 瘦身资讯 ,减肥瘦身健康知识,减肥瘦身信息专题
     * id : 11
     * keywords : 减肥瘦身
     * seq : 1
     * title : 减肥瘦身
     */
    @PrimaryKey
    private int id;
    private String description;
    private String keywords;
    private int seq;
    private String title;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
