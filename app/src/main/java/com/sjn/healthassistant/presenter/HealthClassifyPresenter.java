package com.sjn.healthassistant.presenter;

import com.sjn.healthassistant.common.DataWrapper;
import com.sjn.healthassistant.contarct.ListContract;
import com.sjn.healthassistant.model.HealthModel;
import com.sjn.healthassistant.pojo.HealthClassify;
import com.sjn.healthassistant.pojo.HealthLore;
import com.sjn.healthassistant.pojo.HealthNews;

import java.util.List;

import javax.inject.Inject;

import rx.functions.Action1;

/**
 * Created by sjn on 16/5/4.
 */
public class HealthClassifyPresenter extends BasePresenter<ListContract.View<HealthLore>> implements ListContract.Presenter {


    @Inject
    HealthModel healthModel;


    private int mCurrentPage;//当前所在页码
    private int pageCount;//总共多少页

    public void setHealthClassify(HealthClassify healthClassify) {
        mHealthClassify = healthClassify;
    }

    private HealthClassify mHealthClassify;

    public HealthClassifyPresenter(ListContract.View<HealthNews> view) {
        initPresenterComponent().inject(this);
        mCurrentPage = 1;
    }


    @Override
    public void pullDown() {
        addSubscription(healthModel.getHealthLore(mHealthClassify.getId(), mCurrentPage)
            .compose(this.<DataWrapper<List<HealthLore>>>applySchedulers())
            .subscribe(new Action1<DataWrapper<List<HealthLore>>>() {
                @Override
                public void call(DataWrapper<List<HealthLore>> listDataWrapper) {
                    pageCount = listDataWrapper.getTotal() / 10;
                    mView.onPullDown(listDataWrapper.getTngou());
                }
            }, new Action1<Throwable>() {
                @Override
                public void call(Throwable throwable) {
                    throwable.printStackTrace();
                }
            }));
    }

    @Override
    public void pullUp() {

    }

    public void setPage(int page) {
        mCurrentPage = page;
    }
}
