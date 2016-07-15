package com.tcps.pay.common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import javax.net.ssl.SSLContext;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import com.tcps.pay.service.https.SSLClient;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.Dom4JDriver;
import com.thoughtworks.xstream.io.xml.XmlFriendlyNameCoder;

/**
 * 
 * <p>
 * Title: HttpsRequest
 * </p>
 * <p>
 * Description: 发送https的post请求的类
 * </p>
 * <p>
 * Company: 天津通卡公司
 * </p>
 * 
 * @author yanwenxiong
 * @date 2016年5月27日
 */
public class HttpsRequest {
	// 表示请求器是否已经做了初始化工作
	private boolean hasInit = false;

	// 连接超时时间，默认10秒
	private int socketTimeout = 10000;

	// 传输超时时间，默认30秒
	private int connectTimeout = 30000;

	// 请求器的配置
	private RequestConfig requestConfig;

	// HTTP请求器
	private CloseableHttpClient httpClient;

	// 初始化httpclient，加载了https信任证书。
	public HttpsRequest() throws KeyManagementException,
			UnrecoverableKeyException, KeyStoreException,
			NoSuchAlgorithmException, IOException {
		init();
	}

	public void init() throws KeyStoreException, IOException,
			KeyManagementException, UnrecoverableKeyException,
			NoSuchAlgorithmException {
		/*
		 * KeyStore keyStore = KeyStore.getInstance("PKCS12"); FileInputStream
		 * instream = new FileInputStream(new
		 * File(Configure.getCertLocalPath()));//加载本地的证书进行https加密传输 try {
		 * keyStore.load(instream,
		 * Configure.getCertPassword().toCharArray());//设置证书密码 } catch
		 * (CertificateException e) { e.printStackTrace(); } catch
		 * (NoSuchAlgorithmException e) { e.printStackTrace(); } finally {
		 * instream.close(); }
		 * 
		 * // Trust own CA and all self-signed certs SSLContext sslcontext =
		 * SSLContexts.custom() .loadKeyMaterial(keyStore,
		 * Configure.getCertPassword().toCharArray()) .build(); // Allow TLSv1
		 * protocol only SSLConnectionSocketFactory sslsf = new
		 * SSLConnectionSocketFactory( sslcontext, new String[]{"TLSv1"}, null,
		 * SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
		 * 
		 * httpClient = HttpClients.custom() .setSSLSocketFactory(sslsf)
		 * .build();
		 */

		try {
			httpClient = new SSLClient();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 根据默认超时限制初始化requestConfig
		requestConfig = RequestConfig.custom().setSocketTimeout(socketTimeout)
				.setConnectTimeout(connectTimeout).build();

		hasInit = true;
	}

	// 使用https post xml数据
	public String doPost(String url, Object xmlObject)
			throws KeyManagementException, UnrecoverableKeyException,
			KeyStoreException, NoSuchAlgorithmException, IOException {
		if (!hasInit)
			init();

		StringBuffer sb = new StringBuffer();

		HttpPost post = new HttpPost(url);

		// 获得对应的xmlstr//解决XStream对出现双下划线的bug
		XStream xStream = new XStream(new Dom4JDriver(new XmlFriendlyNameCoder(
				"-_", "_")));
		xStream.alias("xml", xmlObject.getClass());
		String xmlStr = xStream.toXML(xmlObject);

		// 指明用的是utf-8编码，设置entity
		HttpEntity reqEntity = new StringEntity(xmlStr, ContentType.create(
				"plain/text", Consts.UTF_8));
		post.addHeader("Content-Type", "text/xml");
		post.setEntity(reqEntity);

		// 设置请求器的配置
		post.setConfig(requestConfig);

		HttpResponse response = httpClient.execute(post);
		HttpEntity respEntity = response.getEntity();
		if (respEntity != null) {
			BufferedReader br = new BufferedReader(new InputStreamReader(
					respEntity.getContent(), Consts.UTF_8));
			String text = "";
			while ((text = br.readLine()) != null) {
				sb.append(text);
			}
		}
		return sb.toString();
	}

	public int getSocketTimeout() {
		return socketTimeout;
	}

	public void setSocketTimeout(int socketTimeout) {
		this.socketTimeout = socketTimeout;
	}

	public int getConnectTimeout() {
		return connectTimeout;
	}

	public void setConnectTimeout(int connectTimeout) {
		this.connectTimeout = connectTimeout;
	}

	public void resetRequestConfig() {
		requestConfig = RequestConfig.custom().setSocketTimeout(socketTimeout)
				.setConnectTimeout(connectTimeout).build();
		return;
	}

}
