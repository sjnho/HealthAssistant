package com.sjn.healthassistant.presenter;

import android.widget.Toast;

import com.sjn.healthassistant.common.DataWrapper;
import com.sjn.healthassistant.contarct.ListContract;
import com.sjn.healthassistant.model.HealthModel;
import com.sjn.healthassistant.pojo.HealthClassify;
import com.sjn.healthassistant.pojo.HealthLore;
import com.sjn.healthassistant.pojo.HealthNews;
import com.sjn.healthassistant.ui.activity.HealthClassifyListActivity;

import java.util.List;

import javax.inject.Inject;

import rx.functions.Action1;

/**
 * Created by sjn on 16/5/4.
 */
public class HealthClassifyPresenter extends BasePresenter<HealthClassifyListActivity> implements ListContract.Presenter {


    @Inject
    HealthModel healthModel;

    private int mCurrentPage;//当前所在页码


    private int mSettingPage;//设置的页码

    public int getPageCount() {
        return pageCount;
    }

    private int pageCount;//总共多少页

    public void setHealthClassify(HealthClassify healthClassify) {
        mHealthClassify = healthClassify;
    }

    private HealthClassify mHealthClassify;

    public HealthClassifyPresenter() {
        initPresenterComponent().inject(this);
        mCurrentPage = 1;
    }


    @Override
    public void pullDown() {
        addSubscription(healthModel.getHealthLore(mHealthClassify.getId(), mSettingPage <= 1 ? 1 : mSettingPage - 1)
            .compose(this.<DataWrapper<List<HealthLore>>>applySchedulers())
            .subscribe(new Action1<DataWrapper<List<HealthLore>>>() {
                @Override
                public void call(DataWrapper<List<HealthLore>> listDataWrapper) {
                    pageCount = listDataWrapper.getTotal() / 10;
                    mView.onPullDown(listDataWrapper.getTngou());
                    mView.stopLoading();
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
        addSubscription(healthModel.getHealthLore(mHealthClassify.getId(), mCurrentPage)
            .compose(this.<DataWrapper<List<HealthLore>>>applySchedulers())
            .subscribe(new Action1<DataWrapper<List<HealthLore>>>() {
                @Override
                public void call(DataWrapper<List<HealthLore>> listDataWrapper) {
                    mView.onPullUp(listDataWrapper.getTngou());
                }
            }, new Action1<Throwable>() {
                @Override
                public void call(Throwable throwable) {
                    throwable.printStackTrace();
                }
            }));

    }

    public void setPageData(int page) {
        if (page < mCurrentPage) {
            return;
        }
        mSettingPage = page;
        mCurrentPage = page;
        addSubscription(healthModel.getHealthLore(mHealthClassify.getId(), mSettingPage)
            .compose(this.<DataWrapper<List<HealthLore>>>applySchedulers())
            .subscribe(new Action1<DataWrapper<List<HealthLore>>>() {
                @Override
                public void call(DataWrapper<List<HealthLore>> listDataWrapper) {
                    mView.onSetPageData(listDataWrapper.getTngou());
                    mView.stopLoading();
                }
            }, new Action1<Throwable>() {
                @Override
                public void call(Throwable throwable) {
                    mView.stopLoading();
                    throwable.printStackTrace();
                }
            }));
    }

    public void nextPage() {
        mCurrentPage = mCurrentPage + 1;
    }
}
