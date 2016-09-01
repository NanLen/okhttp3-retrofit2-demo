package com.android.library.config;

/**
 * Created by liyanan on 16/4/22.
 */
public class RestConfig {
    //OkHttp要求必须以"/"结尾
    public static String BASE_URL = "http://apis.baidu.com/apistore/";
    //百度天气要求传入header
    public static String API_KEY = "b8e39620c74e4670eb78aabdaa883982";

    public static void init(String baseUrl, String apiKey) {
        BASE_URL = baseUrl;
        API_KEY = apiKey;
    }
}
