package com.android.library.callback;

import android.util.Log;

import com.android.library.listener.RequestPrepareProcessListener;
import com.android.library.response.BaseResponse;
import com.android.library.util.LogUtil;

import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by liyanan on 16/4/22.
 */
public abstract class CallbackImpl<T> implements Callback<T>, RequestPrepareProcessListener {

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (!prepareProcess()) {
            return;
        }
        T t = response.body();
        if (t instanceof BaseResponse) {
            BaseResponse baseResponse = (BaseResponse) t;
            if ("0".equals(baseResponse.getErrNum())) {
                onSuccess(call, response);
            } else {
                onError(call, baseResponse.getErrMsg());
            }
        } else {
            onSuccess(call, response);
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        if (!prepareProcess()) {
            return;
        }
        Log.d("Exception", LogUtil.getLog(t));
        String message = t.getMessage();
        if (t != null && t instanceof UnknownHostException) {
            message = "网络连接失败";
        } else if (t != null && t instanceof SocketTimeoutException) {
            message = "请求超时";
        }
        onError(call, message);
    }

    public abstract void onSuccess(Call<T> call, Response<T> response);

    public abstract void onError(Call<T> call, String error);


}
