package com.jk.week_02;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HttpClientUtils {

    public static void main(String[] args) {
        HttpClientUtils.doGet("http://localhost:8802/");
    }

    public static String doGet(String url) {
        String resultString = "";
        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet(url);
            CloseableHttpResponse response = httpClient.execute(httpGet);
            if (response.getStatusLine().getStatusCode() == 200) {
                resultString = EntityUtils.toString(response.getEntity(), "UTF-8");
                System.out.println(resultString);
                return resultString;
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultString;
    }

    public static String doPost(String url, Map<String,String> params){
        String resultString ="";
        UrlEncodedFormEntity entity = null;
        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(url);
            if(!params.isEmpty()){
                List<NameValuePair> pairs = new ArrayList<>();
                for(Map.Entry<String,String> entry : params.entrySet()){
                    pairs.add(new  BasicNameValuePair(entry.getKey(),entry.getValue()));
                }
                entity = new UrlEncodedFormEntity(pairs,"UTF-8");
            }
            httpPost.setEntity(entity);
            CloseableHttpResponse response = httpClient.execute(httpPost);
            if (response.getStatusLine().getStatusCode() == 200) {
                resultString = EntityUtils.toString(response.getEntity(), "UTF-8");
                System.out.println(resultString);
                return resultString;
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultString;
    }
}
