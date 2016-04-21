package com.android.http.request;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 构建接口service对象
 * Created by liyanan on 16/4/21.
 */
public class RestBuilder {

    public static <T> T build(Class<T> service) {
        return new Retrofit.Builder()
                .baseUrl(RestConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(new OkHttpClient())
                .build()
                .create(service);
    }
}
