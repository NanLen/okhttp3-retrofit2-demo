package com.android.library.interceptor;


import com.android.library.config.RestConfig;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * request拦截器
 * Created by liyanan on 16/4/22.
 */
public class RequestInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Request.Builder builder = request.newBuilder()
                .header("apikey", RestConfig.API_KEY)
                //此处可以定制自己的header
                .header("os", "android");
        return chain.proceed(builder.build());
    }
}
