package com.jk.week_02;

import okhttp3.*;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class OkHttpClientUtil {
    static OkHttpClientUtil okHttpClientUtil;
    private static OkHttpClient httpClient = null;

    private OkHttpClientUtil(){
        httpClient = new OkHttpClient.Builder()
                .connectTimeout(5000, TimeUnit.MILLISECONDS)
                .readTimeout(5000, TimeUnit.MILLISECONDS)
                .writeTimeout(5000, TimeUnit.MILLISECONDS)
                .build();
    }

    public static OkHttpClientUtil init(){
        if (null == okHttpClientUtil) {
            synchronized (OkHttpClientUtil.class) {
                if (null == okHttpClientUtil) {
                    okHttpClientUtil = new OkHttpClientUtil();
                }
            }
        }
        return okHttpClientUtil;
    }

    public static String doGet(String url){
        String resultString = "";
        try {
            Request request = new Request.Builder().url(url).get().build();
            final Call call = httpClient.newCall(request);
            Response response = call.execute();
            resultString = response.body().toString();
            System.out.println(resultString);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultString;
    }

    public static String doPost(String url, Map<String,String> params){
        String resultString = "";
        try {
            FormBody.Builder formBody = new FormBody.Builder();
            for(Map.Entry<String,String> entry : params.entrySet()){
                formBody.add(entry.getKey(), (String) entry.getValue());
            }
            Request request = new Request.Builder().method("POST",formBody.build()).url(url).build();
            final Call call = httpClient.newCall(request);
            Response response = call.execute();
            resultString = response.body().toString();
            System.out.println(resultString);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultString;
    }
}
