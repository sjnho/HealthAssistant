package com.sjn.healthassistant.model;

import com.sjn.healthassistant.network.DataWrapper;
import com.sjn.healthassistant.network.SjnNetwork;
import com.sjn.healthassistant.network.SjnService;

import rx.Observable;

/**
 * Created by sjn on 16/4/19.
 */
public abstract class BaseModel {
    protected SjnService mService;

    public BaseModel() {
        mService = SjnNetwork.getService();
    }
    protected <T> Observable<T>  transData(DataWrapper<T> raw){
        return Observable.just(raw.getTngou());
    }
}
