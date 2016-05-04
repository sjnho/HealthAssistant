package com.sjn.healthassistant.presenter;

import com.sjn.healthassistant.contarct.ListContract;
import com.sjn.healthassistant.model.HealthModel;
import com.sjn.healthassistant.pojo.HealthNews;

import java.util.List;

import javax.inject.Inject;

import rx.functions.Action1;

/**
 * Created by sjn on 16/5/2.
 */
public class HealthPresenter extends BasePresenter<ListContract.View<HealthNews>> implements ListContract.Presenter {


    @Inject
    HealthModel healthModel;

    public HealthPresenter() {
        super();
        initPresenterComponent().inject(this);
    }

    @Override
    public void pullDown() {
        addSubscription(
            healthModel.getHealthNews()
                .compose(this.<List<HealthNews>>applySchedulers())
                .subscribe(new Action1<List<HealthNews>>() {
                    @Override
                    public void call(List<HealthNews> healthInfos) {
                        mView.onPullDown(healthInfos);
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

    }
}
