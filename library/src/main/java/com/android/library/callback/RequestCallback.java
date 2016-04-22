package com.android.library.callback;

import android.app.Activity;
import android.support.v4.app.Fragment;

import java.lang.ref.WeakReference;

/**
 * 请求回调
 * Created by liyanan on 16/4/22.
 */
public abstract class RequestCallback<T> extends CallbackImpl<T> {

    private WeakReference<Activity> activityWeakReference;
    private WeakReference<Fragment> fragmentWeakReference;
    private int type;

    public RequestCallback() {

    }

    public RequestCallback(Activity activity) {
        activityWeakReference = new WeakReference<>(activity);
        type = 1;
    }

    public RequestCallback(Fragment fragment) {
        fragmentWeakReference = new WeakReference<>(fragment);
        type = 2;
    }

    /**
     * 预处理
     * 如果activity或fragment被销毁,不会继续执行onSuccess和onError
     * 默认为true
     *
     * @return
     */
    @Override
    public boolean prepareProcess() {
        if (type == 1) {
            //Activity 请求
            Activity activity = activityWeakReference.get();
            if (activity == null || activity.isFinishing()) {
                return false;
            }

        } else if (type == 2) {
            //Fragment请求
            Fragment fragment = fragmentWeakReference.get();
            if (fragment == null || fragment.isDetached() || fragment.getActivity() == null || fragment.getActivity().isFinishing()) {
                return false;
            }
        }
        return true;
    }
}
