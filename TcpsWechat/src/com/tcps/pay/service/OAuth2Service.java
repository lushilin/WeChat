package com.tcps.pay.service;


import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.tcps.pay.common.Configure;
import com.tcps.pay.common.HttpClientUtil;
import com.tcps.pay.model.oauth2.OAuth2AccessToken;

/**
 * 
 * <p>
 * Title: OAuth2Utils
 * </p>
 * <p>
 * Description: OAuth2.0授权，获取，刷新access_token等操作的工具类
 * </p>
 * <p>
 * Company: 通卡
 * </p>
 * 
 * @author yanwenxiong
 * @date 2016年5月20日
 */
public class OAuth2Service {
	// 获取网页授权accessToken的接口地址。
	public static String INTERFACE_OAUTH2ACCESSTOKEN = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
	
	public static String INTERFACE_REFRESHOAUTH2ACCESSTOKEN = "https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=APPID&grant_type=refresh_token&refresh_token=REFRESH_TOKEN";

	/**
	 * 获取网页授权接口地址返回的response
	 * 
	 * @param String
	 *            code： 用户同意授权后，微信服务器发来的code
	 * @return String oauth2AccessToken:正常返回为网页授权accessToken，否则为空
	 */
	public static String requestOAuth2AccessTokenResponse(String code) {
		HttpClientUtil httpClientUtil = HttpClientUtil.getInstance();
		String response = null;

		String url = INTERFACE_OAUTH2ACCESSTOKEN;
		url = url.replace("APPID", Configure.getAppid());
		url = url.replace("SECRET", Configure.getAppsecret());
		url = url.replace("CODE", code);
		response = httpClientUtil.doGet(url);
		return response;
	}

	/**
	 * 获取OAuth2AccessToken
	 * 
	 * @param String
	 *            code： 用户同意授权后，微信服务器发来的code
	 * @return OAuth2AccessToken
	 *         oAuth2access_token:包含OAuth2AccessToken的bean,异常反应则回复null；
	 */
	public static OAuth2AccessToken getOAuth2AccessToken(String code) {
		OAuth2AccessToken oAuth2access_token = new OAuth2AccessToken();

		String response = requestOAuth2AccessTokenResponse(code);

		if (response != null) {
			try {
				JSONObject jsonObject = new JSONObject(response);
				if (jsonObject.getString("access_token") != null) {
					oAuth2access_token.setAccess_token(jsonObject
							.getString("access_token"));
					oAuth2access_token.setExpires_in(jsonObject
							.getString("expires_in"));
					oAuth2access_token.setRefresh_token(jsonObject
							.getString("refresh_token"));
					oAuth2access_token.setOpenid(jsonObject.getString("openid"));
					oAuth2access_token.setScope(jsonObject.getString("scope"));
					return oAuth2access_token;
				}
			} catch (JSONException e) {

			}
		}

		return null;

	}

	public static OAuth2AccessToken refreshAuth2AccessToken(String refresh_token){
		OAuth2AccessToken oAuth2AccessToken = new OAuth2AccessToken();
		String url = INTERFACE_REFRESHOAUTH2ACCESSTOKEN;
		url = url.replace("APPID", Configure.getAppid());
		url = url.replace("REFRESH_TOKEN", refresh_token);
		
		HttpClientUtil httpClientUtil = HttpClientUtil.getInstance();
		String responseStr = httpClientUtil.doGet(url);
		
		if(responseStr != null){
			try {
				JSONObject object = new JSONObject(responseStr);
				if(object.getString("openid") != null){
					oAuth2AccessToken.setAccess_token(object
							.getString("access_token"));
					oAuth2AccessToken.setExpires_in(object
							.getString("expires_in"));
					oAuth2AccessToken.setRefresh_token(object
							.getString("refresh_token"));
					oAuth2AccessToken.setOpenid(object.getString("openid"));
					oAuth2AccessToken.setScope(object.getString("scope"));
					return oAuth2AccessToken;
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		return null;
	}
}
