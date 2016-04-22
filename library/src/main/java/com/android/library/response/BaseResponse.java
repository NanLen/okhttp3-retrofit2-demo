package com.android.library.response;

/**
 * Created by liyanan on 16/4/22.
 */
public class BaseResponse {
    protected String errNum;//0代表查询成功
    protected String errMsg;

    public String getErrNum() {
        return errNum;
    }

    public void setErrNum(String errNum) {
        this.errNum = errNum;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }
}
