package com.sjn.healthassistant.inject.module;


import com.sjn.healthassistant.inject.scope.PerPresenter;
import com.sjn.healthassistant.model.DiseaseModel;
import com.sjn.healthassistant.model.DrugModel;
import com.sjn.healthassistant.model.HealthModel;
import com.sjn.healthassistant.common.SjnService;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Yi on 16/1/7.
 */

@Module
public class PresenterModule {
    @Provides
    @PerPresenter
    HealthModel provideHealthModel(SjnService api) {
        return new HealthModel(api);
    }

    @Provides
    @PerPresenter
    DrugModel provideDrugModel(SjnService api) {
        return new DrugModel(api);
    }

    @Provides
    @PerPresenter
    DiseaseModel provideDiseaseModel(SjnService api) {
        return new DiseaseModel(api);
    }


}
