package com.sjn.healthassistant.model;

import com.sjn.healthassistant.common.DataWrapper;
import com.sjn.healthassistant.common.SjnService;

import rx.Observable;

/**
 * Created by sjn on 16/4/19.
 */
public abstract class BaseModel {
    protected SjnService mApi;

    public BaseModel(SjnService service) {
        mApi = service;
    }

    protected <T> Observable<T>  transData(DataWrapper<T> raw){
        return Observable.just(raw.getTngou());
    }
}
