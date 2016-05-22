package com.sjn.healthassistant.presenter;

import com.sjn.healthassistant.contarct.DetailContract;
import com.sjn.healthassistant.model.DrugModel;
import com.sjn.healthassistant.pojo.Drug;

import javax.inject.Inject;

import rx.functions.Action1;

/**
 * Created by sjn on 16/5/17.
 */
public class DrugCodePresenter extends BasePresenter<DetailContract.View<Drug>> implements DetailContract.Presenter {

    @Inject
    DrugModel mDrugModel;

    public void setCode(String code) {
        this.code = code;
    }

    private String code;


    public DrugCodePresenter() {
        initPresenterComponent().inject(this);
    }

    @Override
    public void getDeatil() {
        addSubscription(
            mDrugModel.findDrugByCode(code)
                .compose(this.<Drug>applySchedulers())
                .subscribe(new Action1<Drug>() {
                    @Override
                    public void call(Drug drug) {
                        mView.onStopLoading();
                        mView.onGetDetail(drug);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        mView.onStopLoading();
                        throwable.printStackTrace();
                    }
                }));

    }
}
