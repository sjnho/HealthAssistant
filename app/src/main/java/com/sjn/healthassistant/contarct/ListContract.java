package com.sjn.healthassistant.contarct;

import java.util.List;

/**
 * Created by sjn on 16/5/1.
 */
public interface ListContract {
    interface View<T> {

        void onPullDown(List<T> data);

        void onPullUp(List<T> data);

        void stopLoading();
    }

    interface Presenter {
        void pullDown();

        void pullUp();
    }
}
