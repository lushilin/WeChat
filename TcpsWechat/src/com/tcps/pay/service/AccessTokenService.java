package com.tcps.pay.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.tcps.pay.common.CommonUtil;
import com.tcps.pay.common.Configure;
import com.tcps.pay.common.HttpClientUtil;
import com.tcps.pay.common.PayUtill;
              
public class AccessTokenService {
	// 默认存取使用的编码格式
	public static final String charSet = "UTf-8";
	// 微信官方设定的accessToken的过期时间7200，我设定为7000，免去查询过程中用新的还是旧的accessToken的问题。
	public static final int expire = 7000;

	public static String INTERFACE_GETACCESSTOKEN = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";

	/**
	 * 在旧的已经过期时候， 向微信服务器重新请求新的accessToken
	 * 
	 * @return 网络情况和参数正确时候可以正常返回正确值。
	 * @return 不正确时候回返回 null
	 */
	public static String requestAccessTokenResponse() {
		HttpClientUtil httpClientUtil = HttpClientUtil.getInstance();
		String response = null;
		String url = INTERFACE_GETACCESSTOKEN;
		url = url.replace("APPID", Configure.getAppid());
		url = url.replaceFirst("APPSECRET", Configure.getAppsecret());
		response = httpClientUtil.doGet(url);
		return response;
	}

	/*
	 * 向微信接口查询accessToken
	 * 
	 * @return 网络正常时候返回accessToken
	 * 
	 * @return 网络失败时候返回null
	 */
	public static String requestAccessToken() {
		String respStr = requestAccessTokenResponse();
		String accessToken = null;
		if (respStr != null) {
			try {
				JSONObject jsonObject = new JSONObject(respStr);
				accessToken = jsonObject.getString("access_token");
			} catch (Exception e) {

			}

		}
		return accessToken;
	}

	/**
	 * 获取accessToken
	 * 
	 * @return accessToken: 一切正常时候的返回值。
	 * @return null: 过期，或者文件读取失败时候的返回值。
	 */
	public static String getAccessToken() {
		String content = null;
		String[] contentArr = null;
		content = CommonUtil.readTextFile(new File(
				"./WebRoot/File/accessToken.txt"), null);
		// 如果读取到了内容，则从记录中获取accessToken
		if (content != null && !("".equals(content))) {
			contentArr = content.split(" ");
			if (contentArr.length == 2) {
				String accessToken = contentArr[0];
				String startStr = contentArr[1];
				System.out.println(accessToken + " " + startStr);
				// 判断现在accessToken是否还在有效期内
				long start = Long.parseLong(startStr);
				long now = System.currentTimeMillis();
				long passTime = now - start;
				// 解释一下，这里是毫秒，所以乘以1000
				if (passTime <= expire * 1000) {
					return accessToken;
				}

			}
		}
		// 否则就查询接口，返回accessToken；

		String accessToken = requestAccessToken();
		long start = System.currentTimeMillis();
		if (accessToken != null && start != 0) {
			CommonUtil.writeToFile(new File("./WebRoot/File/accessToken.txt"),
					accessToken + " " + start);
		}
		return accessToken;
	}

}
