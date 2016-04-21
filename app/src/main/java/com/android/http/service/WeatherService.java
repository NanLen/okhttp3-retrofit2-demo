package com.android.http.service;

import com.android.http.bean.WeatherResponse;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by liyanan on 16/4/21.
 */
public interface WeatherService {

    @GET("/data/sk/101010100.html")
    Call<WeatherResponse> getWeather();
}
