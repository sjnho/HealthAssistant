package com.sjn.healthassistant.presenter;

import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import com.sjn.healthassistant.contarct.ListContract;
import com.sjn.healthassistant.model.DiseaseModel;
import com.sjn.healthassistant.pojo.Department;
import com.sjn.healthassistant.pojo.Symptom;
import com.sjn.healthassistant.ui.activity.SymptomListActivity;
import com.sjn.healthassistant.util.RealmGson;

import java.util.List;

import javax.inject.Inject;

import io.realm.Realm;
import rx.functions.Action1;

/**
 * Created by sjn on 16/5/22.
 */
public class SyptomListPresenter extends BasePresenter<SymptomListActivity> implements ListContract.Presenter {

    @Inject
    DiseaseModel mDiseaseModel;

    private Department mDepartment;


    private int mCurrentPage;//当前所在页码

    private int pageCount;//总共多少页

    private int mSettingPage;//设置的页码

    public void setDepartment(Department department) {
        mDepartment = department;
    }

    public SyptomListPresenter() {
        initPresenterComponent().inject(this);
        mCurrentPage = 1;
    }


    public int getPageCount() {
        return pageCount;
    }

    @Override
    public void pullDown() {
        addSubscription(
            mDiseaseModel.getSymptom(mSettingPage <= 1 ? 1 : mSettingPage - 1, mDepartment.getId())
                .compose(this.<JsonElement>applySchedulers())
                .subscribe(new Action1<JsonElement>() {
                    @Override
                    public void call(JsonElement jsonElement) {
                        pageCount = jsonElement.getAsJsonObject().get("totalpage").getAsInt();
                        List<Symptom> list = RealmGson.getGson().fromJson(jsonElement.getAsJsonObject().get("list"), new TypeToken<List<Symptom>>() {
                        }.getType());
                        mView.stopLoading();
                        mView.onPullDown(list);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        mView.stopLoading();
                        mView.showToast(throwable.getMessage());
                        throwable.printStackTrace();
                    }
                })
        );
    }

    @Override
    public void pullUp() {
        addSubscription(
            mDiseaseModel.getSymptom(mCurrentPage, mDepartment.getId())
                .compose(this.<JsonElement>applySchedulers())
                .subscribe(new Action1<JsonElement>() {
                    @Override
                    public void call(JsonElement jsonElement) {
                        List<Symptom> list = RealmGson.getGson().fromJson(jsonElement.getAsJsonObject().get("list"), new TypeToken<List<Symptom>>() {
                        }.getType());
                        mView.onPullDown(list);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        mView.showToast(throwable.getMessage());
                        throwable.printStackTrace();
                    }
                })
        );
    }

    public void setPageDate(int page) {
        if (page < mCurrentPage) {
            return;
        }
        mSettingPage = page;
        mCurrentPage = page;

    }

    public void nextPage() {
        mCurrentPage = mCurrentPage + 1;
    }
}
