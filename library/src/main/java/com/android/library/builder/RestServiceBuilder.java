package com.android.library.builder;

import com.android.library.config.RestConfig;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 构建service接口对象
 * Created by liyanan on 16/4/22.
 */
public class RestServiceBuilder {
    public static <T> T buildService(Class<T> service) {
        return new Retrofit.Builder()
                .baseUrl(RestConfig.BASE_URL)
                //此处解析器可自由替换
                .addConverterFactory(GsonConverterFactory.create())
                .client(RestClientBuilder.buildClient())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()
                .create(service);
    }
}
