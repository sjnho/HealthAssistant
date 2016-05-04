package com.sjn.healthassistant.common;

import com.sjn.healthassistant.pojo.Drug;
import com.sjn.healthassistant.pojo.HealthLore;
import com.sjn.healthassistant.pojo.HealthNews;

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
    @GET("drug/show")
    Observable<Drug> drugDetail(@Query("id")int id);//获得药品详情




    /**
     * 健康信息
     */
    @GET("info/news")
    Observable<DataWrapper<List<HealthNews>>> newInfos(@Query("id") int id);//获取最新的健康资讯

    @GET("lore/list")
    Observable<DataWrapper<List<HealthLore>>> getHealthLore(@Query("id")int id,@Query("page")int page,@Query("rows")int rows);//获取健康分类知识

    /**
     * 疾病
     */
}
