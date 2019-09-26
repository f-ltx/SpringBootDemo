package cn.ltx.springboot.controller.test;

import cn.hutool.http.HttpUtil;
import cn.ltx.springboot.utils.Utils3DES;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TestHttpClient {
    public static void main(String[] args) {
        String methods = "syncJgjCase";
        JSONObject datas = new JSONObject();
        datas.put("username", "gzjgxt");
        datas.put("password", "gzjgxt2019");
        JSONObject data = new JSONObject();
        data.put("casename", "案件名称");
        data.put("caseno", "案件编号");
        datas.put("data", data);
//        httpClient(methods, Utils3DES.encryptString(datas.toJSONString()));
        hutoolHttp(methods, Utils3DES.encryptString(datas.toJSONString()));
    }

    public static void httpClient(String method, String datas) {
        // 获得Http客户端(可以理解为:你得先有一个浏览器;注意:实际上HttpClient与浏览器是不一样的)
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        // 参数
        URI uri = null;
        HttpPost httpPost = null;
        try {
            // 将参数放入键值对类NameValuePair中,再放入集合中
            List<NameValuePair> params = new ArrayList<>();
            params.add(new BasicNameValuePair("method", method));
            params.add(new BasicNameValuePair("datas", datas));
            HttpEntity reqEntity = new UrlEncodedFormEntity(params, "utf-8");
            // 设置uri信息,并将参数集合放入uri;
            // 注:这里也支持一个键值对一个键值对地往里面放setParameter(String key, String value)
            uri = new URIBuilder().setScheme("http").setHost("localhost").setPort(8080).setPath("/syncJgjCase").setParameters(params).build();
            httpPost = new HttpPost(uri);

            // post请求是将参数放在请求体里面传过去的;这里将entity放入post请求体中
            httpPost.setEntity(reqEntity);


            httpPost.setHeader("Content-Type", "application/json;charset=utf8");        // 响应模型
        } catch (URISyntaxException e1) {
            e1.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        CloseableHttpResponse response = null;
        try {            // 由客户端执行(发送)Post请求
            response = httpClient.execute(httpPost);            // 从响应模型中获取响应实体
            HttpEntity responseEntity = response.getEntity();
            System.out.println("响应状态为:" + response.getStatusLine());
            if (responseEntity != null) {
                System.out.println("响应内容长度为:" + responseEntity.getContentLength());
                System.out.println("响应内容为:" + EntityUtils.toString(responseEntity));
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {                // 释放资源
                if (httpClient != null) {
                    httpClient.close();
                }
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public static void hutoolHttp(String method, String datas) {
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("method", method);
        paramMap.put("datas", datas);
        System.out.println(datas);
        String result= HttpUtil.post("http://localhost:8080/syncJgjCase", paramMap);
    }
}
