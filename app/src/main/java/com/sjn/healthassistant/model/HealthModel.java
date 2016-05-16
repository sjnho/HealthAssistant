package com.sjn.healthassistant.model;

import com.sjn.healthassistant.common.DataWrapper;
import com.sjn.healthassistant.common.SjnService;
import com.sjn.healthassistant.pojo.HealthLore;
import com.sjn.healthassistant.pojo.HealthNews;

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

    public Observable<List<HealthNews>> getHealthNews() {
        return mApi.newInfos(0, 1).concatMap(new Func1<DataWrapper<List<HealthNews>>, Observable<DataWrapper<List<HealthNews>>>>() {
            @Override
            public Observable<DataWrapper<List<HealthNews>>> call(DataWrapper<List<HealthNews>> listDataWrapper) {

                return mApi.newInfos(listDataWrapper.getTotal(), 10);
            }
        }).concatMap(new Func1<DataWrapper<List<HealthNews>>, Observable<? extends List<HealthNews>>>() {
            @Override
            public Observable<? extends List<HealthNews>> call(DataWrapper<List<HealthNews>> listDataWrapper) {
                return transData(listDataWrapper);
            }
        });

    }

    public Observable<DataWrapper<List<HealthLore>>> getHealthLore(int id, int page) {
        return mApi.getHealthLore(id, page, 10);
    }

}
