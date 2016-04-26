package com.sjn.healthassistant.network;

import com.sjn.healthassistant.pojo.Drug;
import com.sjn.healthassistant.pojo.HealthInfo;

import java.util.List;

import retrofit2.http.Query;
import rx.Observable;
import retrofit2.http.GET;

/**
 * Created by sjn on 16/4/19.
 */
public interface SjnService {
    /**
     * 药品
     */
    @GET("/drug/show")
    Observable<Drug> drugDetail(@Query("id")int id);//获得药品详情




    /**
     * 健康信息
     */
    @GET("/info/news")
    Observable<DataWrapper<List<HealthInfo>>> newInfos(@Query("id") int id);//获取最新的健康资讯
    /**
     * 疾病
     */
}
