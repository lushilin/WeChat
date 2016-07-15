package com.tcps.java.common.util;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;


public class OAuthPermisson {

	public String urlTransform(String transformURL){
		try {
			transformURL = URLEncoder.encode(transformURL,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String url = "https://open.weixin.qq.com/connect/oauth2/authorize?"
				+"appid=wx7e244b5ad1f1e1b9"
				+"&redirect_uri="+transformURL
				+"&response_type=code"
				+"&scope=snsapi_base"
				+"&state=a#wechat_redirect";
		return url;
		
	}
}
