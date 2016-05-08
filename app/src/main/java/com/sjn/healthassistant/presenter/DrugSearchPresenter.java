package com.sjn.healthassistant.presenter;

import com.sjn.healthassistant.contarct.ListContract;
import com.sjn.healthassistant.model.DrugModel;
import com.sjn.healthassistant.pojo.Drug;
import com.sjn.healthassistant.ui.activity.DrugSearchActivity;

import java.util.List;

import javax.inject.Inject;

import rx.functions.Action1;

/**
 * Created by sjn on 16/5/8.
 */
public class DrugSearchPresenter extends BasePresenter<DrugSearchActivity> implements ListContract.Presenter {
    @Inject
    DrugModel mDrugModel;


    private int mCurrentPage;

    public void setKeyWord(String keyWord) {
        mKeyWord = keyWord;
        mCurrentPage = 1;
    }

    private String mKeyWord;

    public DrugSearchPresenter() {
        initPresenterComponent().inject(this);
    }

    @Override
    public void pullDown() {
        addSubscription(
            mDrugModel.searchDrug(mKeyWord, mCurrentPage)
                .compose(this.<List<Drug>>applySchedulers())
                .subscribe(new Action1<List<Drug>>() {
                    @Override
                    public void call(List<Drug> drugs) {
                        mView.onPullDown(drugs);
                        mView.stopLoading();
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        throwable.printStackTrace();
                    }
                })
        );
    }

    @Override
    public void pullUp() {
        addSubscription(
            mDrugModel.searchDrug(mKeyWord, mCurrentPage)
                .compose(this.<List<Drug>>applySchedulers())
                .subscribe(new Action1<List<Drug>>() {
                    @Override
                    public void call(List<Drug> drugs) {
                        mView.onPullUp(drugs);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        throwable.printStackTrace();
                    }
                })
        );
    }

    public void nextPage() {
        mCurrentPage++;
    }
}
