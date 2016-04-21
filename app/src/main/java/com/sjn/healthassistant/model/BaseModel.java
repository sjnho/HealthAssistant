package com.sjn.healthassistant.model;

import com.sjn.healthassistant.network.SjnNetwork;
import com.sjn.healthassistant.network.SjnService;

/**
 * Created by sjn on 16/4/19.
 */
public abstract class BaseModel {
    protected SjnService mService;

    public BaseModel() {
        mService = SjnNetwork.getService();
    }
}
