package com.android.library.callback;

/**
 * 请求预处理listener
 * Created by liyanan on 16/4/22.
 */
public interface RequestPrepareProcessListener {
    /**
     * 预处理,如果activity或fragment销毁之后
     * 不执行onSuccess和onError
     *
     * @return true继续执行, false终止处理
     */
    boolean prepareProcess();
}
