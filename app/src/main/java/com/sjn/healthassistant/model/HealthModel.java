package com.sjn.healthassistant.model;

import com.sjn.healthassistant.pojo.HealthInfo;

import java.util.List;

import rx.Observable;

/**
 * Created by sjn on 16/4/21.
 */
public class HealthModel extends BaseModel {
    /**
     * 实际上应该拿最新的ID,但是是倒序的
     * @return
     */
    public Observable<List<HealthInfo>>  getHealthNews(){
        return mService.newInfos(6485).concatMap(this::transData);
    }
}
