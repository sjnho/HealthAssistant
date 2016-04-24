package com.sjn.healthassistant.network;

import com.sjn.healthassistant.pojo.HealthInfo;

import rx.Observable;
import retrofit2.http.GET;

/**
 * Created by sjn on 16/4/19.
 */
public interface SjnService {
    /**
     * 药品
     */

    /**
     * 健康信息
     */
    @GET("/info/news")
    Observable<DataWrapper<HealthInfo>> getNewInfo();//获取最新的健康资讯,默认传5
    /**
     * 疾病
     */
}
