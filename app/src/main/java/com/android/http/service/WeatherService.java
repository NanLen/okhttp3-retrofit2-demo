package com.android.http.service;


import com.android.http.response.WeatherResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * 业务接口类
 * Created by liyanan on 16/4/21.
 */
public interface WeatherService {

    /**
     * 获取某个城市的天气
     *
     * @param cityName
     * @return
     */
    @GET("weatherservice/cityname")
    Call<WeatherResponse> getWeather(@Query("cityname") String cityName);

    @GET("weatherservice/cityname")
    Observable<WeatherResponse> getRxWeather(@Query("cityname") String cityName);
}
