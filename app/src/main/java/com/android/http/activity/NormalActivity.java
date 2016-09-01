package com.android.http.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.android.http.R;
import com.android.http.response.WeatherResponse;
import com.android.http.service.WeatherService;
import com.android.library.builder.RestServiceBuilder;
import com.android.library.callback.RequestCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by liyanan on 16/5/3.
 */
public class NormalActivity extends AppCompatActivity {
    @BindView(R.id.tv_weather_info)
    TextView tvWeatherInfo;
    private Call<WeatherResponse> call;
    private Unbinder unbinder;
    private String city;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal);
        unbinder = ButterKnife.bind(this);
        city = getIntent().getStringExtra("city");
        getWeather();
    }

    private void getWeather() {
        WeatherService service = RestServiceBuilder.buildService(WeatherService.class);
        call = service.getWeather(city);
        call.enqueue(new RequestCallback<WeatherResponse>(this) {
            @Override
            public void onSuccess(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                tvWeatherInfo.setText(response.body().getRetData().toString());
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
        unbinder.unbind();
    }
}
