package com.sjn.healthassistant.inject.component;


import com.sjn.healthassistant.contarct.ListContract;
import com.sjn.healthassistant.inject.module.PresenterModule;
import com.sjn.healthassistant.inject.scope.PerPresenter;
import com.sjn.healthassistant.pojo.HealthClassify;
import com.sjn.healthassistant.presenter.DrugSearchPresenter;
import com.sjn.healthassistant.presenter.HealthClassifyPresenter;
import com.sjn.healthassistant.presenter.HealthPresenter;

import dagger.Component;

/**
 * Created by Yi on 16/1/7.
 */
@PerPresenter
@Component(dependencies = AppComponent.class, modules = PresenterModule.class)
public interface PresenterComponent {
    void inject(HealthPresenter healthPresenter);

    void inject(HealthClassifyPresenter healthClassifyPresenter);

    void inject(DrugSearchPresenter drugSearchPresenter);


}
