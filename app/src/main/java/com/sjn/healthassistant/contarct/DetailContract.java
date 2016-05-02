package com.sjn.healthassistant.contarct;

/**
 * Created by sjn on 16/5/1.
 */
public interface DetailContract {
    interface View<T> {
        void setPresenter(Presenter presenter);
        void onGetDetail(T detail);
    }

    interface Presenter {
        void getDeatil();
    }
}
