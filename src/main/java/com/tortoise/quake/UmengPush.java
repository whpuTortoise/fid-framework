package com.tortoise.quake;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import com.tortoise.framework.util.JsonUtil;

/**  
 * @Project: quake
 * @Title: UmengPush.java
 * @Package com.tortoise.quake
 * @Description: TODO
 * @author WangZhi
 * @date 2018年5月8日 下午1:53:16
 * @Copyright: 2018 
 * @version V1.0  
 */

public class UmengPush {
	
	public static final String HOST = "http://msg.umeng.com";
	
	public static final String POST_PATH = "/api/send";
	
	/**
	 * android对应的配置
	 */
	public static final String ANDROID_APP_MASTER_SECRET = "luz8h1lk8mwqw3ap406pr8smp12uacsv";
	private static final String ANDROID_APP_KEY = "5ade88b78f4a9d54ef0002a2";
	
	/**
	 * ios对应的配置
	 */
	public static final String IOS_APP_MASTER_SECRET = "qgjjiynhu5nsbvxzhefzzxxifu9dw2gx";
	private static final String IOS_APP_KEY = "5add971df29d9829410000da";
	
	private static final int ANDROID = 1;
	
	private static final int IOS = 2;
	
	
	public static void sendMessageDemo(){
		Map<String, String> extra = new HashMap<String, String>();
		extra.put("key123", "key123");
		Map<String, String> params = getSendParam(IOS, "b37f075656bae3184de36b2fecebe2125711ed645bfc9a19a70e9bdbde66c497", "您有一条新通知", "通知", "3号逆变器异常", extra);
		try {
			sendMessage(IOS, JsonUtil.toJson(params));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	* @Description: TODO 获取发送参数
	* @param type 消息类型：1为Android，2为ios
	* @param device_tokens 设备标识
	* @param ticker 通知栏提示文字
	* @param title 通知标题
	* @param text 通知文字描述
	* @param extra 附加参数     
	* @return Map<String, String> 
	* @throws
	 */
	private static Map<String, String> getSendParam(int type, String device_tokens, String ticker, String title, String text, Map<String, String> extra){
		Map<String, String> params = new HashMap<String, String>();
		if(type == ANDROID){ //android消息
			params.put("appkey", ANDROID_APP_KEY);
		}else if(type == IOS){
			params.put("appkey", IOS_APP_KEY);
		}
		
		String timestamp = Integer.toString((int)(System.currentTimeMillis() / 1000));
		params.put("timestamp", timestamp); // 必填，时间戳，10位或者13位均可，时间戳有效期为10分钟
		params.put("type", "unicast"); // 必填，消息发送类型
		params.put("device_tokens", device_tokens); // 必填，表示指定的单个设备
		Map<String, Object> payload = new HashMap<String, Object>();
		
		if(type == ANDROID){ //android消息
			payload.put("display_type", "notification"); // 必填，消息类型: notification(通知)、message(消息)
			Map<String, String> body = new HashMap<String, String>(); // 当display_type=message时，body的内容只需填写custom字段。// 当display_type=notification时，body包含如下参数
			body.put("ticker", ticker); // 必填，通知栏提示文字
			body.put("title", title); // 必填，通知标题
			body.put("text", text); // 必填，通知文字描述
			body.put("play_vibrate", "true"); // 可选，收到通知是否震动，默认为"true"
			body.put("play_lights", "true"); // 可选，收到通知是否闪灯，默认为"true"
			body.put("play_sound", "true"); // 可选，收到通知是否发出声音，默认为"true"
			payload.put("body", body); // 必填，消息体。
			payload.put("extra", extra); //可选，JSON格式，用户自定义key-value。只对"通知"
		}else if(type == IOS){ //ios消息
			Map<String, Object> aps = new HashMap<String, Object>();
			Map<String, String> alert = new HashMap<String, String>();
			alert.put("title", ticker);
			alert.put("subtitle", title);
			alert.put("body", text);
			aps.put("alert", alert);
			aps.put("sound", "default");
			payload.put("aps", aps); 
			
			if(extra != null){
				for(Map.Entry<String,String> entry : extra.entrySet()){
					payload.put(entry.getKey(), entry.getValue());
				}
			}
		}
		
		params.put("payload", JsonUtil.toJson(payload)); // 必填，JSON格式，具体消息内容(Android最大为1840B, iOS最大为2012B)
		params.put("description", title); // 可选，发送消息描述，建议填写。  
		params.put("production_mode", "false");
		
		
		return params;
	}
	
	
	
	/**
	 * 
	* @Title: send 推送消息
	* @Description: TODO
	* @param  jsonStr
	
	 */
	public static boolean sendMessage(int type, String jsonStr) throws Exception {
		CloseableHttpClient httpclient = HttpClientBuilder.create().build();
		CloseableHttpResponse response = null;
		
		 String url = HOST + POST_PATH;
		 String sign = "";
		 if(type == ANDROID){ //android消息
			 sign = DigestUtils.md5Hex(("POST" + url + jsonStr + ANDROID_APP_MASTER_SECRET).getBytes("utf8"));
		}else if(type == IOS){
			sign = DigestUtils.md5Hex(("POST" + url + jsonStr + IOS_APP_MASTER_SECRET).getBytes("utf8"));
		}
	     
	     url = url + "?sign=" + sign;
		
		HttpPost httppost = new HttpPost(url);
		if (jsonStr != null) {
			StringEntity strEntity = new StringEntity(jsonStr, "UTF-8");
			strEntity.setContentType("application/json");
			httppost.setEntity(strEntity);
			try {
				response = httpclient.execute(httppost);
				HttpEntity entity = response.getEntity();
				if (entity != null) {
			        int status = response.getStatusLine().getStatusCode();
			        System.out.println("Response Code : " + status);
			        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			        StringBuffer result = new StringBuffer();
			        String line = "";
			        while ((line = rd.readLine()) != null) {
			            result.append(line);
			        }
			        System.out.println(result.toString());
			        if (status == 200) {
			            System.out.println("Notification sent successfully.");
			        } else {
			            System.out.println("Failed to send the notification!");
			        }
				}
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				// 关闭连接,释放资源
				try {
					if (response != null) {
						response.close();
					}

					if (httpclient != null) {
						httpclient.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
        
        return true;
    }
	
}
