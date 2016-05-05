package com.android.library.observer;

import android.util.Log;

import com.android.library.listener.RequestPrepareProcessListener;
import com.android.library.response.BaseResponse;
import com.android.library.util.LogUtil;

import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import rx.Observer;

/**
 * Created by liyanan on 16/5/5.
 */
public abstract class ObserverImpl<T> implements Observer<T>, RequestPrepareProcessListener {

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        if (!prepareProcess()) {
            return;
        }
        Log.d("Exception", LogUtil.getLog(e));
        String message = e.getMessage();
        if (e != null && e instanceof UnknownHostException) {
            message = "网络连接失败";
        } else if (e != null && e instanceof SocketTimeoutException) {
            message = "请求超时";
        }
        onError(message);
    }

    @Override
    public void onNext(T t) {
        if (!prepareProcess()) {
            return;
        }
        if (t instanceof BaseResponse) {
            BaseResponse baseResponse = (BaseResponse) t;
            if ("0".equals(baseResponse.getErrNum())) {
                onSuccess(t);
            } else {
                onError(baseResponse.getErrMsg());
            }
        } else {
            onSuccess(t);
        }
    }

    public abstract void onSuccess(T t);

    public abstract void onError(String error);


}
