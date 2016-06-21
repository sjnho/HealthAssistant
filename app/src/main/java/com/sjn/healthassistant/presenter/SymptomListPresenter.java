package com.sjn.healthassistant.presenter;

import android.content.SharedPreferences;

import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import com.sjn.healthassistant.common.Constants;
import com.sjn.healthassistant.contarct.ListContract;
import com.sjn.healthassistant.model.DiseaseModel;
import com.sjn.healthassistant.pojo.BodyPart;
import com.sjn.healthassistant.pojo.Symptom;
import com.sjn.healthassistant.ui.activity.SymptomListActivity;
import com.sjn.healthassistant.util.RealmGson;

import java.util.List;

import javax.inject.Inject;

import rx.functions.Action1;

/**
 * Created by sjn on 16/5/22.
 */
public class SymptomListPresenter extends BasePresenter<SymptomListActivity> implements ListContract.Presenter {

    @Inject
    DiseaseModel mDiseaseModel;
    @Inject
    SharedPreferences mSp;


    private BodyPart mBodyPart;


    private int mCurrentPage;//当前所在页码

    private int pageCount;//总共多少页

    private int mSettingPage;//设置的页码

    public void setBodyPart(BodyPart bodyPart) {
        mBodyPart = bodyPart;
        mCurrentPage = mSp.getInt(bodyPart.getId() + Constants.SP_SYMPTOM_PAGE, 1);
    }

    public SymptomListPresenter() {
        initPresenterComponent().inject(this);
    }


    public int getPageCount() {
        return pageCount;
    }

    @Override
    public void pullDown() {
        addSubscription(
            mDiseaseModel.getSymptom(mSettingPage <= 1 ? 1 : mSettingPage - 1, mBodyPart.getId())
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
            mDiseaseModel.getSymptom(mCurrentPage, mBodyPart.getId())
                .compose(this.<JsonElement>applySchedulers())
                .subscribe(new Action1<JsonElement>() {
                    @Override
                    public void call(JsonElement jsonElement) {
                        List<Symptom> list = RealmGson.getGson().fromJson(jsonElement.getAsJsonObject().get("list"), new TypeToken<List<Symptom>>() {
                        }.getType());
                        mView.onPullUp(list);
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

    public void setPageData(int page) {
        if (page < mCurrentPage) {
            return;
        }
        mSettingPage = page;
        mCurrentPage = page;
        addSubscription(
            mDiseaseModel.getSymptom(mCurrentPage, mBodyPart.getId())
                .compose(this.<JsonElement>applySchedulers())
                .subscribe(new Action1<JsonElement>() {
                    @Override
                    public void call(JsonElement jsonElement) {
                        List<Symptom> list = RealmGson.getGson().fromJson(jsonElement.getAsJsonObject().get("list"), new TypeToken<List<Symptom>>() {
                        }.getType());
                        mView.onSetPage(list);
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

    public void nextPage() {
        mCurrentPage = mCurrentPage + 1;
    }

    @Override
    public void destroy() {
        super.destroy();
        mSp.edit().putInt(mBodyPart.getId() + Constants.SP_SYMPTOM_PAGE, mCurrentPage).apply();
    }
}
