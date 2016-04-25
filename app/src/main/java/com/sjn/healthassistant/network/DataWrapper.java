package com.sjn.healthassistant.network;

/**
 * Created by sjn on 16/4/19.
 */
public class DataWrapper<T> {


    public T getTngou() {
        return tngou;
    }

    public void setTngou(T tngou) {
        this.tngou = tngou;
    }

    T tngou;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    int total;

}
