package com.sjn.healthassistant.presenter;

import android.support.annotation.NonNull;

import com.sjn.healthassistant.contarct.ListContract;
import com.sjn.healthassistant.model.HealthModel;
import com.sjn.healthassistant.pojo.HealthInfo;

import java.util.List;

import javax.inject.Inject;

import rx.functions.Action1;

/**
 * Created by sjn on 16/5/2.
 */
public class HealthPresenter extends BasePresenter implements ListContract.Presenter {

    private ListContract.View<HealthInfo> mView;

    @Inject
    HealthModel healthModel;

    public HealthPresenter(@NonNull ListContract.View view) {
        super();
        initPresenterComponent().inject(this);
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void pullDown() {
        addSubscription(
                healthModel.getHealthNews()
                        .compose(this.<List<HealthInfo>>applySchedulers())
                        .subscribe(new Action1<List<HealthInfo>>() {
                            @Override
                            public void call(List<HealthInfo> healthInfos) {
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
