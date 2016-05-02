package com.sjn.healthassistant.presenter;

import com.sjn.healthassistant.SjnApplication;
import com.sjn.healthassistant.inject.component.DaggerPresenterComponent;
import com.sjn.healthassistant.inject.component.PresenterComponent;
import com.sjn.healthassistant.inject.module.PresenterModule;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by sjn on 16/5/1.
 */
public class BasePresenter {
    private CompositeSubscription mSubscriptions;

    public BasePresenter() {
        this.mSubscriptions = new CompositeSubscription();
    }

    protected <T> Observable.Transformer<T, T> applySchedulers() {
        return new Observable.Transformer<T, T>() {
            @Override
            public Observable<T> call(Observable<T> tObservable) {
                return tObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
            }
        };
    }
    /**
     * 添加一个订阅者
     *
     * @param sub
     */
    protected void addSubscription(Subscription sub) {
        mSubscriptions.add(sub);
    }

    public void unsubscribe() {
        mSubscriptions.clear();
    }

    protected PresenterComponent initPresenterComponent() {
        return DaggerPresenterComponent
                .builder()
                .appComponent(SjnApplication.getAppComponent())
                .build();
    }
}
