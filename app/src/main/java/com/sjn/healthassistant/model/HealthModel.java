package com.sjn.healthassistant.model;

import com.sjn.healthassistant.common.DataWrapper;
import com.sjn.healthassistant.common.SjnService;
import com.sjn.healthassistant.pojo.HealthInfo;

import java.util.List;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by sjn on 16/4/21.
 */
public class HealthModel extends BaseModel {
    public HealthModel(SjnService service) {
        super(service);
    }

    public Observable<List<HealthInfo>> getHealthNews() {
        return mApi.newInfos(0).concatMap(new Func1<DataWrapper<List<HealthInfo>>, Observable<DataWrapper<List<HealthInfo>>>>() {
            @Override
            public Observable<DataWrapper<List<HealthInfo>>> call(DataWrapper<List<HealthInfo>> listDataWrapper) {
                return mApi.newInfos(listDataWrapper.getTotal());
            }
        }).concatMap(new Func1<DataWrapper<List<HealthInfo>>, Observable<? extends List<HealthInfo>>>() {
            @Override
            public Observable<? extends List<HealthInfo>> call(DataWrapper<List<HealthInfo>> listDataWrapper) {
                return transData(listDataWrapper);
            }
        });

    }
}
