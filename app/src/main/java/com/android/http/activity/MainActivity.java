package com.android.http.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.android.http.R;
import com.android.http.response.WeatherResponse;
import com.android.http.response.bean.Weather;
import com.android.http.service.WeatherService;
import com.android.library.builder.RestServiceBuilder;
import com.android.library.callback.RequestCallback;

import retrofit2.Call;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    TextView tvWeatherInfo;

    private Call<WeatherResponse> call;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvWeatherInfo = (TextView) findViewById(R.id.tv_weather_info);
        getWeather();
    }

    private void getWeather() {
        WeatherService service = RestServiceBuilder.buildService(WeatherService.class);
        call = service.getWeather("上海");
        call.enqueue(new RequestCallback<WeatherResponse>(this) {
            @Override
            public void onSuccess(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                Weather weather = response.body().getRetData();
                tvWeatherInfo.setText(weather.getCity() + weather.getWeather());
            }

            @Override
            public void onError(Call<WeatherResponse> call, String error) {
                tvWeatherInfo.setText(error);

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (call != null && call.isExecuted()) {
            call.cancel();
        }
    }
}
