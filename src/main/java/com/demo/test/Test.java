package com.demo.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * 
 *                                        ,s555SB@@&                         
 *                                      :9H####@@@@@Xi                       
 *                                     1@@@@@@@@@@@@@@8                      
 *                                   ,8@@@@@@@@@B@@@@@@8                     
 *                                  :B@@@@X3hi8Bs;B@@@@@Ah,                  
 *             ,8i                  r@@@B:     1S ,M@@@@@@#8;                
 *            1AB35.i:               X@@8 .   SGhr ,A@@@@@@@@S               
 *            1@h31MX8                18Hhh3i .i3r ,A@@@@@@@@@5              
 *            ;@&i,58r5                 rGSS:     :B@@@@@@@@@@A              
 *             1#i  . 9i                 hX.  .: .5@@@@@@@@@@@1              
 *              sG1,  ,G53s.              9#Xi;hS5 3B@@@@@@@B1               
 *               .h8h.,A@@@MXSs,           #@H1:    3ssSSX@1                 
 *               s ,@@@@@@@@@@@@Xhi,       r#@@X1s9M8    .GA981              
 *               ,. rS8H#@@@@@@@@@@#HG51;.  .h31i;9@r    .8@@@@BS;i;         
 *                .19AXXXAB@@@@@@@@@@@@@@#MHXG893hrX#XGGXM@@@@@@@@@@MS       
 *                s@@MM@@@hsX#@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&,     
 *              :GB@#3G@@Brs ,1GM@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@B,    
 *            .hM@@@#@@#MX 51  r;iSGAM@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@8    
 *          :3B@@@@@@@@@@@&9@h :Gs   .;sSXH@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@:   
 *      s&HA#@@@@@@@@@@@@@@M89A;.8S.       ,r3@@@@@@@@@@@@@@@@@@@@@@@@@@@r   
 *   ,13B@@@@@@@@@@@@@@@@@@@5 5B3 ;.         ;@@@@@@@@@@@@@@@@@@@@@@@@@@@i   
 *  5#@@#&@@@@@@@@@@@@@@@@@@9  .39:          ;@@@@@@@@@@@@@@@@@@@@@@@@@@@;   
 *  9@@@X:MM@@@@@@@@@@@@@@@#;    ;31.         H@@@@@@@@@@@@@@@@@@@@@@@@@@:   
 *   SH#@B9.rM@@@@@@@@@@@@@B       :.         3@@@@@@@@@@@@@@@@@@@@@@@@@@5   
 *     ,:.   9@@@@@@@@@@@#HB5                 .M@@@@@@@@@@@@@@@@@@@@@@@@@B   
 *           ,ssirhSM@&1;i19911i,.             s@@@@@@@@@@@@@@@@@@@@@@@@@@S  
 *              ,,,rHAri1h1rh&@#353Sh:          8@@@@@@@@@@@@@@@@@@@@@@@@@#: 
 *            .A3hH@#5S553&@@#h   i:i9S          #@@@@@@@@@@@@@@@@@@@@@@@@@A.
 *
 *
 * @author 厉昀键
 * create in 2018年8月20日
 * java 调用webservice接口及接口参数传递实例 包含get和post方法
 *
 */
public class Test {
	
	static HttpClient httpClient = HttpClientBuilder.create().build();
	
	public static void main(String[] args) {
		
		String getUrl = "http://localhost:8089/userTest/index";
		String postUrl = "http://localhost:8089/userTest/oneUpdate";
		int httpCodeByGet = submitByGet(getUrl);
		System.out.println("get请求方法返回的状态码为:" + httpCodeByGet);
		int httpCodeByPost = submitByPost(postUrl);
		System.out.println("post请求方法返回的状态码为:" + httpCodeByPost);
	}
	
	public static int submitByGet(String url){
		
		HttpGet request = new HttpGet(url);
		request.addHeader("content-type", "application/json");
		request.addHeader("Accept", "application/json");
		try {
			HttpResponse httpResponse = httpClient.execute(request);
			int httpCode =  httpResponse.getStatusLine().getStatusCode();
			return httpCode;
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 1111;
	}
	
	public static int submitByPost(String url){
		
		HttpPost httpPost = new HttpPost(url);
		//设置请求头
		httpPost.setHeader("Content-Type","application/x-www-form-urlencoded; charset=UTF-8");
		//携带参数提交
		NameValuePair userName = new BasicNameValuePair("userName","joeson");
		List<NameValuePair> list = new ArrayList<NameValuePair>();
        list.add(userName);
		try {
			httpPost.setEntity(new UrlEncodedFormEntity(list, "UTF-8"));
			HttpResponse httpResponse = httpClient.execute(httpPost);
			int httpCode =  httpResponse.getStatusLine().getStatusCode();
			String response = EntityUtils.toString(httpResponse.getEntity());
			System.out.println(response.toString());
			JsonObject json = new JsonParser().parse(response.toString()).getAsJsonObject();
			System.out.println("sessionID = " + json.get("sessionID"));
			return httpCode;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 1111;
	}
	
}
