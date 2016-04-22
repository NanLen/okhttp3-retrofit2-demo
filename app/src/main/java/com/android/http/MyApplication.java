package com.android.http;

import android.app.Application;

import com.android.http.constant.ApplicationConstant;
import com.android.library.config.RestConfig;

/**
 * Created by liyanan on 16/4/22.
 */
public class MyApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        RestConfig.init(ApplicationConstant.BASE_URL,ApplicationConstant.API_KEY);
    }
}
