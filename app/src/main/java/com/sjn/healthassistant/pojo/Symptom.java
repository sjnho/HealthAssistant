package com.sjn.healthassistant.pojo;

/**
 * Created by sjn on 16/5/19.
 */
public class Symptom {
    private String name;//疾病名称
    private String img;//图片
    private String keywords;
    private String description;
    private String department;//疾病科室
    private String place;//疾病部位
    private String message;//简介，摘要
    private String disease;//相关疾病
    private String causetext;//病因
    private String drug;//相关药品
    private String detailtext;//诊断详情
    private String checks;//检测项目

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getDrug() {
        return drug;
    }

    public void setDrug(String drug) {
        this.drug = drug;
    }

    public String getChecks() {
        return checks;
    }

    public void setChecks(String checks) {
        this.checks = checks;
    }

    public String getDetailtext() {
        return detailtext;
    }

    public void setDetailtext(String detailtext) {
        this.detailtext = detailtext;
    }

    public String getCausetext() {
        return causetext;
    }

    public void setCausetext(String causetext) {
        this.causetext = causetext;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }
}
