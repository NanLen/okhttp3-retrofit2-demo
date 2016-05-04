package com.android.http.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.android.http.R;
import com.android.http.bus.RxBus;
import com.android.http.bus.event.SwitchCityEvent;
import com.jakewharton.rxbinding.view.RxView;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import rx.functions.Action1;
import rx.subscriptions.CompositeSubscription;


public class MainActivity extends AppCompatActivity {
    private Unbinder unbinder;
    @BindView(R.id.tv_request1)
    TextView tvRequest1;
    @BindView(R.id.tv_request2)
    TextView tvRequest2;
    @BindView(R.id.tv_switch_city)
    TextView tvSwitchCity;
    @BindView(R.id.tv_current_city)
    TextView tvCurrentCity;
    private CompositeSubscription subscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        unbinder = ButterKnife.bind(this);
        subscription = new CompositeSubscription();
        registerEventReceiver();
        clickSwitchCity();
        clickRequest1();
        clickRequest2();
    }

    private void clickSwitchCity() {
        subscription.add(RxView.clicks(tvSwitchCity).throttleFirst(200, TimeUnit.MILLISECONDS).subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid) {
                startActivity(new Intent(MainActivity.this, SwitchCityActivity.class));
            }
        }));
    }

    private void clickRequest1() {
        //过滤点击事件
        subscription.add(RxView.clicks(tvRequest1).throttleFirst(200, TimeUnit.MILLISECONDS).subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid) {
                Intent intent = new Intent(MainActivity.this, NormalActivity.class);
                intent.putExtra("city", tvCurrentCity.getText().toString());
                startActivity(intent);
            }
        }));
    }

    private void clickRequest2() {
        subscription.add(RxView.clicks(tvRequest2).throttleFirst(200, TimeUnit.MILLISECONDS).subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid) {
                Intent intent = new Intent(MainActivity.this, RxJavaActivity.class);
                intent.putExtra("city", tvCurrentCity.getText().toString());
                startActivity(intent);
            }
        }));
    }

    private void registerEventReceiver() {
        subscription.add(RxBus.getInstance().toObserverable().subscribe(new Action1<Object>() {
            @Override
            public void call(Object o) {
                if (o instanceof SwitchCityEvent) {
                    SwitchCityEvent event = (SwitchCityEvent) o;
                    tvCurrentCity.setText(event.getCity());
                }
            }
        }));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        subscription.unsubscribe();
    }
}
