package com.android.http.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.android.http.R;
import com.android.http.bean.WeatherResponse;
import com.android.http.request.RestBuilder;
import com.android.http.service.WeatherService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    TextView tvWeatherInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvWeatherInfo = (TextView) findViewById(R.id.tv_weather_info);
        getWeather();
    }

    private void getWeather() {
        WeatherService service = RestBuilder.build(WeatherService.class);
        service.getWeather().enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                WeatherResponse.WeatherInfo weatherInfo = response.body().getWeatherInfo();
                tvWeatherInfo.setText(weatherInfo.getCity() + weatherInfo.getWD());
            }

            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {

            }
        });
    }


}
