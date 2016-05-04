package com.android.http.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.android.http.R;
import com.android.http.response.WeatherResponse;
import com.android.http.response.bean.Weather;
import com.android.http.service.WeatherService;
import com.android.library.builder.RestServiceBuilder;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class RxJavaActivity extends AppCompatActivity {
    @BindView(R.id.tv_weather_info)
    TextView tvWeatherInfo;

    private CompositeSubscription subscription;
    private Unbinder unbinder;
    private String city;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava);
        unbinder = ButterKnife.bind(this);
        subscription = new CompositeSubscription();
        city = getIntent().getStringExtra("city");
        getWeather();
    }

    private void getWeather() {
        WeatherService service = RestServiceBuilder.buildService(WeatherService.class);
        subscription.add(service.getRxWeather(city).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<WeatherResponse>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(WeatherResponse weatherResponse) {
                Weather weather = weatherResponse.getRetData();
                tvWeatherInfo.setText(weather.toString());
            }
        }));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        subscription.unsubscribe();
        unbinder.unbind();
    }
}
