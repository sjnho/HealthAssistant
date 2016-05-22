package com.sjn.healthassistant.model;

import com.google.gson.JsonElement;
import com.sjn.healthassistant.common.SjnService;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by sjn on 16/4/21.
 */
public class DiseaseModel extends BaseModel {

    public DiseaseModel(SjnService service) {
        super(service);
    }

    public Observable<JsonElement> getSymptom(int page, int id) {
        return mApi.getSymptom(page, 10, id);
    }

}
