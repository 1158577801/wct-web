package com.cn.wct.http;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;



/*********************************************************************************
//* Copyright (C) 2014 Pingan Haoche (PAHAOCHE). All Rights Reserved.
//*
//* Filename:      HttpClientObject.java
//* Revision:      1.0
//* Author:       罗大海
//* Created On:    2014年6月26日
//* Modified by:   
//* Modified On:   
//*
//* Description:   <description>
/********************************************************************************/

@SuppressWarnings("deprecation")
public class HttpClientObject<T> {

    private  Logger logger = Logger.getLogger(HttpClientObject.class);
    private  String url;
    private  T      t;

    public HttpClientObject() {

    }
    public static void main(String[] args) {
    	//http://localhost:8080/onlineVideoWebApp/doApp?userName=admin&invokeMethod=login&dataMap={"userPwd":"242343434"}
    		HttpClientObject s=new HttpClientObject();
    		Map<String,String> param =new HashMap<String,String>();
    		param.put("userName", "admin");
    		param.put("invokeMethod", "login");
    		param.put("timestamp", System.currentTimeMillis()+"");
    		JSONObject json=new JSONObject();
    		json.put("userPwd", "242343434");
    		
    		param.put("dataMap",JSON.toJSONString(json) );
    		
    		try {
				String a=s.post("http://localhost:8080/onlineVideoWebApp/doApp", param);
				System.out.println(a);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    
	}
    /**
     * HttpClient工具类
     * @param url
     * @param param:Map<String,String>
     * @return
     * @throws Exception
     */
    public String post(String url,Map<String,String> param)throws Exception{
    	if(param.isEmpty() || StringUtils.isBlank(url))
    		throw new Exception("请求参数不合法");
    	System.out.println("=========请求URL:"+url+"=========参数："+JSON.toJSONString(param));
		Iterator<String> it = param.keySet().iterator();
		List<NameValuePair> list = new ArrayList<NameValuePair>();
		NameValuePair nv = null;
		String key = "";
		while(it.hasNext()){
			key = it.next();
			nv = new BasicNameValuePair(key,(String)param.get(key));
			list.add(nv);
		}
		HttpClientObject<NameValuePair> client = new HttpClientObject<NameValuePair>();
		client.setUrl(url);
		String rsjson = client.doPost(list);
		if(StringUtils.isBlank(rsjson))
			throw new Exception("请求接口无结果 ，url = "+url);
		return rsjson;
	}
    
    public String doPost(List<NameValuePair> list) {

        String result = null;
        try {

            HttpEntity requesthttpEntity = new UrlEncodedFormEntity(list, "UTF-8");

            HttpPost httpPost = new HttpPost(this.url);

            httpPost.setEntity(requesthttpEntity);
            HttpClient httpClient = new DefaultHttpClient();

            httpClient.getParams().setParameter("http.connection.timeout", Integer.valueOf(20000));

            httpClient.getParams().setParameter("http.socket.timeout", Integer.valueOf(20000));

            this.logger.debug(httpPost.getURI());
            StringBuffer sb = new StringBuffer();
            for (NameValuePair p : list) {
                sb.append("&" + p.getName() + "=" + p.getValue());
            }
            this.logger.debug("param=" + sb.toString());
            HttpResponse httpResponse = httpClient.execute(httpPost);
            if (httpResponse.getStatusLine().getStatusCode() != 200) {
                throw new Exception("Failed : HTTP error code : " + httpResponse.getStatusLine().getStatusCode());
            }

            HttpEntity httpEntity = httpResponse.getEntity();
            result = EntityUtils.toString(httpEntity);
        }
        catch (Exception e) {
        	logger.error("异常007"+e.getMessage());
        }
        return result;
    }

    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @return the t
     */
    public T getT() {
        return t;
    }

    /**
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @param t the t to set
     */
    public void setT(T t) {
        this.t = t;
    }
}
