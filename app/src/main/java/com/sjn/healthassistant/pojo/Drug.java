package com.sjn.healthassistant.pojo;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by sjn on 16/4/26.
 */
public class Drug extends RealmObject {

    @PrimaryKey
    private String id;
    private String blfy;//不良反应
    private String drugName;//药品名称
    private String img;//图片地址
    private String jj;//禁忌
    private String manu;//生产企业
    private String pzwh;//批准文号
    private String syz;//适应症
    private String type;//药品类别
    private String yfyl;//用法用量
    private String ywxhzy;//药物互相作用
    private String zc;//贮藏
    private String zycf;//主要成分
    private String zysx;//注意事项
    private String zzjb;//主治疾病

    public String getBlfy() {
        return blfy;
    }

    public void setBlfy(String blfy) {
        this.blfy = blfy;
    }

    public String getDrugName() {
        return drugName;
    }

    public void setDrugName(String drugName) {
        this.drugName = drugName;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getJj() {
        return jj;
    }

    public void setJj(String jj) {
        this.jj = jj;
    }

    public String getManu() {
        return manu;
    }

    public void setManu(String manu) {
        this.manu = manu;
    }

    public String getPzwh() {
        return pzwh;
    }

    public void setPzwh(String pzwh) {
        this.pzwh = pzwh;
    }

    public String getSyz() {
        return syz;
    }

    public void setSyz(String syz) {
        this.syz = syz;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getYfyl() {
        return yfyl;
    }

    public void setYfyl(String yfyl) {
        this.yfyl = yfyl;
    }

    public String getYwxhzy() {
        return ywxhzy;
    }

    public void setYwxhzy(String ywxhzy) {
        this.ywxhzy = ywxhzy;
    }


    public String getZc() {
        return zc;
    }

    public void setZc(String zc) {
        this.zc = zc;
    }

    public String getZycf() {
        return zycf;
    }

    public void setZycf(String zycf) {
        this.zycf = zycf;
    }

    public String getZysx() {
        return zysx;
    }

    public void setZysx(String zysx) {
        this.zysx = zysx;
    }

    public String getZzjb() {
        return zzjb;
    }

    public void setZzjb(String zzjb) {
        this.zzjb = zzjb;
    }
}
