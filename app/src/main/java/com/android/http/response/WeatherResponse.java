package com.android.http.response;

import com.android.http.response.bean.Weather;
import com.android.library.response.BaseResponse;

/**
 * Created by liyanan on 16/4/22.
 */
public class WeatherResponse extends BaseResponse {
    private Weather retData;

    public Weather getRetData() {
        return retData;
    }

    public void setRetData(Weather retData) {
        this.retData = retData;
    }
}
