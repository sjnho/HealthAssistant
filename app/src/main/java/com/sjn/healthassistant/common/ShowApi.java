package com.sjn.healthassistant.common;

import com.google.gson.JsonElement;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by sjn on 16/5/21.
 */
public interface ShowApi {
    @GET("93-97")
    Observable<JsonElement> searchDrug(@Query("keyword") String keyword, @Query("page") int page, @Query("limit") int li, @Query("manu") String manu);

}
