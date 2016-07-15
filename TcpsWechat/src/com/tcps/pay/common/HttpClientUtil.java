package com.tcps.pay.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.naming.spi.DirStateFactory.Result;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;


public class HttpClientUtil {

	public static final String CHARSET = "UTF-8";

	private static final int socketTimeout = 10000;// 连接超时时间，10秒
	private static final int connectTimeout = 30000;// 传输超时时间， 30秒
	private static final int maxTotal = 600;// 连接池最大并发连接数
	private static final int maxPerRoute = 300;// 单路由最大并发数

	private static HttpClientUtil httpClientUtil;
	private static CloseableHttpClient httpClient;

	private HttpClientUtil() {
		init();
	}

	public synchronized static HttpClientUtil getInstance() {
		if (httpClientUtil == null) {
			httpClientUtil = new HttpClientUtil();
		}
		return httpClientUtil;
	}

	private void init() {
		PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
		cm.setMaxTotal(maxTotal);
		cm.setDefaultMaxPerRoute(maxPerRoute);
		RequestConfig config = RequestConfig.custom()
				.setConnectTimeout(connectTimeout)
				.setSocketTimeout(socketTimeout).build();
		httpClient = HttpClientBuilder.create().setConnectionManager(cm)
				.setDefaultRequestConfig(config).build();
	}

	/**
	 * http 的 get方法
	 * 
	 * @param String
	 *            url： 要访问的url
	 */
	public String doGet(String url) {
		if (url == null || "".equals(url)) {
			return null;
		}

		try {
			HttpGet httpGet = new HttpGet(url);
			CloseableHttpResponse response = httpClient.execute(httpGet);

			int statusCode = response.getStatusLine().getStatusCode();
			StringBuffer result = new StringBuffer();
			if (statusCode == 200) {
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					BufferedReader br = new BufferedReader(
							new InputStreamReader(entity.getContent(),
									Consts.UTF_8));
					String text = null;
					while ((text = br.readLine()) != null) {
						result.append(text);
					}
					br.close();
				}
				if (result != null)
					return result.toString();
			}
			response.close();
		} catch (Exception e) {
			// TODO
		}
		return null;
	}

	/**
	 * http post entity。
	 * 
	 * @param String
	 *            : 要访问的url。
	 * @param String
	 *            ： 发送的字符串
	 * @return String:返回回复。
	 */
	public String doPostStringEntity(String url, String sendStr) {
		try {
			if (url == null || "".equals(url)) {
				return null;
			}
			StringEntity entity = new StringEntity(sendStr, CHARSET);
			entity.setChunked(true);
			HttpPost httpPost = new HttpPost(url);
		    httpPost.addHeader("Content-Type", "text/xml");
			httpPost.addHeader("Connection", "keep-alive");
			httpPost.addHeader("Accept", "*/*");
			httpPost.addHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.0) ");
			httpPost.setEntity(entity);

			ResponseHandler<String> responseHandler = new ResponseHandler<String>() {
				@Override
				public String handleResponse(final HttpResponse response)
						throws ClientProtocolException, IOException {
					int statusCode = response.getStatusLine().getStatusCode();
					if (statusCode == 200) {
						HttpEntity entity = response.getEntity();
						String result = null;
						if (entity != null) {
							result = EntityUtils.toString(entity, CHARSET);
						}
						return result;
					}
					throw new ClientProtocolException("response status: "
							+ statusCode);
				}
			};

			return httpClient.execute(httpPost, responseHandler);
		} catch (Exception e) {
			// log.error("doPostStringEntity异常", e);
			return null;
		}
	}
}
