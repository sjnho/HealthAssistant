package com.sjn.healthassistant.model;

import com.sjn.healthassistant.common.DataWrapper;
import com.sjn.healthassistant.common.SjnService;
import com.sjn.healthassistant.pojo.Drug;

import java.util.List;

import io.realm.Realm;
import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by sjn on 16/4/21.
 */
public class DrugModel extends BaseModel {

    public DrugModel(SjnService service) {
        super(service);
    }

    public Observable<List<Drug>> searchDrug(String keyword, int page) {
        return mApi.search("drug", keyword, page).concatMap(new Func1<DataWrapper<List<Drug>>, Observable<? extends List<Drug>>>() {
            @Override
            public Observable<? extends List<Drug>> call(DataWrapper<List<Drug>> listDataWrapper) {
                Realm.getDefaultInstance().beginTransaction();
                Realm.getDefaultInstance().copyToRealmOrUpdate(listDataWrapper.getTngou());
                Realm.getDefaultInstance().commitTransaction();
                return transData(listDataWrapper);
            }
        });
    }

    public Observable<Drug> findDrugByCode(String code) {
        final Realm realm = Realm.getDefaultInstance();
        Drug drug = realm.where(Drug.class).equalTo("drug.code", code).findFirst();
        if (drug == null) {
            return mApi.findDrugByCode(code).flatMap(new Func1<Drug, Observable<Drug>>() {
                @Override
                public Observable<Drug> call(Drug drug) {
                    realm.beginTransaction();
                    realm.copyToRealmOrUpdate(drug);
                    realm.commitTransaction();
                    return Observable.just(drug);
                }
            });

        } else {
            return Observable.just(drug);
        }
    }
}
