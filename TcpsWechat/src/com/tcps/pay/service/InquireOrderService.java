package com.tcps.pay.service;

import java.util.Map;

import com.tcps.pay.common.Configure;
import com.tcps.pay.common.HttpClientUtil;
import com.tcps.pay.common.Signature;
import com.tcps.pay.common.XMLHandler;
import com.tcps.pay.model.inquireOrder.InquireReqData;
import com.tcps.pay.model.inquireOrder.InquireRespData;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.Dom4JDriver;
import com.thoughtworks.xstream.io.xml.XmlFriendlyNameCoder;

public class InquireOrderService {
	// 查询一次失败后再次查询的次数
	public static int inquireTimes = 3;

	// 查询一次失败后等待一段时间再次进行查询
	public static int waitTime = 2000;

	/**
	 * 向微信服务器查询订单的状态。
	 * 
	 * @param InquireReqData
	 *            inqureReqData:要向接口发送的数据
	 * @return: InquireRespData为空时候说明失败，不为空说明成功了。
	 */
	public static InquireRespData inquireOrder(InquireReqData inquireReqData) {
		InquireRespData data = null;
		for (int i = 0; i < inquireTimes; i++) {
			data = inquireOrderOnce(inquireReqData);
			if (data != null) {
				return data;
			} else {
				try {
					Thread.sleep(waitTime);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return data;
	}

	/**
	 * 一次向微信服务器查询订单的状态。
	 * 
	 * @param InquireReqData
	 *            inqureReqData:要向接口发送的数据
	 * @return: InquireRespData为空时候说明失败，不为空说明成功了。
	 */
	public static InquireRespData inquireOrderOnce(InquireReqData inqureReqData) {
		if (inqureReqData == null) {
			return null;
		}

		String reqStr, respStr = null;

		XStream xStream = new XStream(new Dom4JDriver(new XmlFriendlyNameCoder(
				"-_", "_")));
		xStream.alias("xml", InquireReqData.class);
		reqStr = xStream.toXML(inqureReqData);

		HttpClientUtil httpClientUtil = HttpClientUtil.getInstance();
		respStr = httpClientUtil.doPostStringEntity(
				Configure.INTERFACE_INQUIREORDER, reqStr);

		if (respStr == null) {
			return null;
		} else {
			InquireRespData respData = (InquireRespData) XMLHandler
					.simpleXMLStrToObj(respStr, InquireRespData.class);
			String return_code = respData.getReturn_code();
			String result_code = respData.getResult_code();
			if ("SUCCESS".equals(return_code) && "SUCCESS".equals(result_code)) {
				String sign = respData.getSign();
				Map<String, String> map = XMLHandler.XMLStrToMap(respStr);
				map.remove("sign");
				String ourSign = Signature.getSign(map);
				if (!sign.equals(ourSign)) {
					// TODO 抛出异常，警告数据的非法性。
				} else {
					return respData;
				}
			}
		}
		return null;
	}
}
