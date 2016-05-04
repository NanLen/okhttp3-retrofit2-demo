package com.android.http.activity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.http.R;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import rx.Observable;
import rx.Subscription;
import rx.functions.Action1;

/**
 * Created by liyanan on 16/5/4.
 */
public class WelcomeActivity extends AppCompatActivity {
    private Subscription subscription;
    @BindView(R.id.iv_image)
    ImageView ivImage;
    @BindView(R.id.tv_text)
    TextView tvText;

    private Unbinder unbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        unbinder = ButterKnife.bind(this);
        startAnimation();
        goToMain();
    }

    private void startAnimation() {
        AnimatorSet set = new AnimatorSet();
        ObjectAnimator imageAnim = ObjectAnimator.ofFloat(ivImage, "alpha", 0, 1);
        imageAnim.setDuration(2000);
        ObjectAnimator textAnim = ObjectAnimator.ofFloat(tvText, "alpha", 0, 1);
        imageAnim.setDuration(2000);
        set.playTogether(imageAnim, textAnim);
        set.start();
    }

    private void goToMain() {
        //延迟5秒发射数据
        subscription = Observable.timer(5, TimeUnit.SECONDS).subscribe(new Action1<Long>() {
            @Override
            public void call(Long aLong) {
                startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
                finish();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        subscription.unsubscribe();
        unbinder.unbind();
    }
}
