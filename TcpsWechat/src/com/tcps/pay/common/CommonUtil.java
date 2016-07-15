package com.tcps.pay.common;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.tcps.pay.service.AccessTokenService;
import com.tcps.pay.service.https.HttpsClientUtil;

public class CommonUtil {

	/**
	 * 读取文件
	 * 
	 * @param File
	 *            file：要读取的text文件
	 * @param String
	 *            charset： 要读取文件的编码格式。
	 */
	public static String readTextFile(File file, String charset) {
		if (charset == null) {
			charset = "UTF-8";
		}

		try {
			if (file.isFile() && file.exists()) {
				FileInputStream fileInputStream = new FileInputStream(file);
				InputStreamReader inputStreamReader = new InputStreamReader(
						fileInputStream, charset);
				BufferedReader bufferedReader = new BufferedReader(
						inputStreamReader);
				StringBuffer sb = new StringBuffer();
				String text = null;
				if ((text = bufferedReader.readLine()) != null) {
					sb.append(text);
				}

				bufferedReader.close();
				inputStreamReader.close();
				fileInputStream.close();
				return sb.toString();
			}
		} catch (Exception e) {

		}
		return null;
	}

	/**
	 * 向特定文件写入内容。
	 * 
	 * @param File file：要写入的文件
	 * @param String content:要写入的内容
	 */
	public static void writeToFile(File file, String content) {
		// 默认将使用utf-8的编码格式。
		writeToFile(file, content, "UTF-8");
	}

	public static void writeToFile(File file, String content, String charset) {
		try {
			if (file.isFile() && file.exists()) {
				FileOutputStream fileOutputStream = new FileOutputStream(file);
				fileOutputStream.write(content.getBytes(charset));
				fileOutputStream.flush();
				fileOutputStream.close();
			}
		} catch (Exception e) {

		}

	}
	
	public static void createMenu() throws JSONException{
		JSONObject jsonObject = new JSONObject();
		JSONObject view = new JSONObject();
		view.put("type", "view");
		view.put("name", "transfer money");
		view.put("url", "https://open.weixin.qq.com/connect/oauth2/authorize?"
					+ "appid=wx7e244b5ad1f1e1b9&"
					+ "redirect_uri=http%3A%2F%2Fwww.yanwenxiong.cn%2FWechat%2Fservlet%2FOAuth2Servlet&"
					+ "response_type=code&"
					+ "scope=snsapi_userinfo&"
					+ "state=STATE#wechat_redirect");
		jsonObject.append("button", view);
		System.out.println(jsonObject.toString());
	
		HttpsClientUtil httpsClientUtil = new HttpsClientUtil();
		String access_token = AccessTokenService.getAccessToken();
		String urlString = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
		urlString = urlString.replace("ACCESS_TOKEN", access_token);
		String response = httpsClientUtil.doPost(urlString, jsonObject.toString(), null);
		System.out.println(response);
	}

}
