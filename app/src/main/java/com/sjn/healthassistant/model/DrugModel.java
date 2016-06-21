package com.sjn.healthassistant.model;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.sjn.healthassistant.SjnApplication;
import com.sjn.healthassistant.common.SjnService;
import com.sjn.healthassistant.pojo.Drug;
import com.sjn.healthassistant.pojo.TnDrug;
import com.sjn.healthassistant.util.RealmGson;

import java.util.List;

import io.realm.Realm;
import rx.Observable;
import rx.functions.Func1;

/**
 * Created by sjn on 16/4/21.
 */
public class DrugModel extends BaseModel {

    public DrugModel(SjnService service) {
        super(service);
    }


    public Observable<List<Drug>> searchDrug(String keyword, int page, String manu) {
        return SjnApplication
                .getAppComponent()
                .getShowApi()
                .searchDrug(keyword, page, 10, manu)
                .concatMap(new Func1<JsonElement, Observable<? extends List<Drug>>>() {
                    @Override
                    public Observable<? extends List<Drug>> call(JsonElement jsonElement) {
                        JsonObject body = jsonElement.getAsJsonObject().get("showapi_res_body").getAsJsonObject();
                        if (body.get("ret_code").getAsInt() == 0) {
                            List<Drug> drugs = RealmGson.getGson().fromJson(body.get("drugList"),
                                new TypeToken<List<Drug>>() {}.getType());
                            Realm.getDefaultInstance().beginTransaction();
                            Realm.getDefaultInstance().copyToRealmOrUpdate(drugs);
                            Realm.getDefaultInstance().commitTransaction();
                            return Observable.just(drugs);
                        } else {
                            return Observable.error(new Throwable(body.get("msg").getAsString()));
                        }
                    }
                });
    }

    public Observable<Drug> findDrugByCode(String code) {
        return mApi.findDrugByCode(code).flatMap(new Func1<TnDrug, Observable<Drug>>() {
            @Override
            public Observable<Drug> call(TnDrug tnDrug) {
                return searchDrug(tnDrug.getName(), 1, tnDrug.getFactory()).map(new Func1<List<Drug>, Drug>() {
                    @Override
                    public Drug call(List<Drug> drugs) {
                        if (drugs.isEmpty()) {
                            return null;
                        }
                        return drugs.get(0);
                    }
                });
            }
        }).flatMap(new Func1<Drug, Observable<Drug>>() {
            @Override
            public Observable<Drug> call(Drug drug) {
                if (drug != null) {
                    Realm.getDefaultInstance().beginTransaction();
                    Realm.getDefaultInstance().copyToRealmOrUpdate(drug);
                    Realm.getDefaultInstance().commitTransaction();
                }
                return Observable.just(drug);
            }
        });
    }
}
