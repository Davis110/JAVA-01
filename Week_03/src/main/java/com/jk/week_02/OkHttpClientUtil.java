package com.jk.week_02;

import com.alibaba.fastjson.JSON;
import okhttp3.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class OkHttpClientUtil {
    private static final Logger    LOGGER          = LoggerFactory.getLogger(OkHttpClientUtil.class);
    public static final  MediaType MEDIA_TYPE_JSON = MediaType.parse("application/json; charset=utf-8");
    private static final byte[]    LOCKER          = new byte[0];

    private static OkHttpClient okHttpClient;

    public static OkHttpClient getInstance() {
        if (okHttpClient == null) {
            synchronized (LOCKER) {
                if (okHttpClient == null) {
                    okHttpClient = new OkHttpClient.Builder()
                            .connectTimeout(30, TimeUnit.SECONDS)
                            .readTimeout(20, TimeUnit.SECONDS)
                            .build();
                }
            }
        }
        return okHttpClient;
    }

    /**
     * Get请求
     *
     * @param url URL地址
     * @return 返回结果
     */
    public static String get(String url) {
        String result = null;
        try {
            Request request = new Request.Builder().url(url).build();
            Response response = getInstance().newCall(request).execute();
            result = response.body().string();
            LOGGER.info("Get请求返回：{}", result);
            return result;
        } catch (Exception e) {
            LOGGER.error("OkHttp[Get]请求异常", e);
            return result;
        }
    }

    /**
     * Post请求
     *
     * @param url    URL地址
     * @param params 参数
     * @return 返回结果
     */
    public static String post(String url, Map<String, Object> params) {
        String result = null;
        if (params == null) {
            params = new HashMap<String, Object>();
        }
        try {
            //入参
            String paramsJson = JSON.toJSONString(params);
            LOGGER.info("paramsJson：{}", paramsJson);
            //请求body
            RequestBody body = RequestBody.create(MEDIA_TYPE_JSON, paramsJson);

            Request request = new Request.Builder()
                    .url(url).post(body).build();
            Response response = getInstance().newCall(request).execute();
            result = response.body().string();
            LOGGER.info("Post请求返回：{}", result);
            return result;
        } catch (Exception e) {
            LOGGER.error("OkHttp[Post]请求异常", e);
            return result;
        }
    }

    /**
     * Post请求
     *
     * @param url     URL地址
     * @param content 参数
     * @return 返回结果
     */
    public static String post(String url, String content) {
        String result = null;
        if (StringUtils.isBlank(content)) {
            return result;
        }
        try {
            LOGGER.info("paramsJson：{}", content);
            //请求body
            RequestBody body = RequestBody.create(MEDIA_TYPE_JSON, content);

            Request request = new Request.Builder()
                    .url(url).post(body).build();
            Response response = getInstance().newCall(request).execute();
            result = response.body().string();
            LOGGER.info("Post请求返回：{}", result);
            return result;
        } catch (Exception e) {
            LOGGER.error("OkHttp[Post]请求异常", e);
            return result;
        }
    }

    /**
     * 上传文件请求（Post请求）
     *
     * @param url    URL地址
     * @param params 文件 key：参数名 value：文件 （可以多文件）
     * @return 返回结果
     */
    public static String upload(String url, Map<String, File> params) {
        String result = null;
        try {
            MultipartBody.Builder multipartBodyBuilder = new MultipartBody.Builder().setType(MultipartBody.FORM);

            for (Map.Entry<String, File> map : params.entrySet()) {
                String key = map.getKey();
                File file = map.getValue();
                if (file == null || (file.exists() && file.length() == 0)) {
                    continue;
                }
                multipartBodyBuilder.addFormDataPart(key, file.getName(), RequestBody.create(MediaType.parse("multipart/form-data"), file));
            }
            RequestBody requestBody = multipartBodyBuilder.build();
            Request request = new Request.Builder().url(url).post(requestBody).build();
            Response response = getInstance().newCall(request).execute();
            result = response.body().string();
            LOGGER.info("Upload请求返回：{}", result);
            return result;
        } catch (Exception e) {
            LOGGER.error("OkHttp[Upload]请求异常", e);
            return result;
        }
    }
}
