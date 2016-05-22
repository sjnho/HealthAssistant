package com.sjn.healthassistant.common;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by sjn on 16/5/21.
 */
public class ShowApiInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {

        Request origin = chain.request();
        Request newRequest;
        HttpUrl httpUrl = origin.url().newBuilder().addQueryParameter("showapi_appid", "19286").addQueryParameter("showapi_sign", "f2173efa39384fbaa9e638fbd0aea826").build();
        newRequest = origin.newBuilder().url(httpUrl).build();
        return chain.proceed(newRequest);
    }
}
