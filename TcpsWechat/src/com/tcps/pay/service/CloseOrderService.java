package com.tcps.pay.service;

import java.util.HashMap;
import java.util.Map;

import com.tcps.pay.common.Configure;
import com.tcps.pay.common.HttpClientUtil;
import com.tcps.pay.common.Signature;
import com.tcps.pay.common.XMLHandler;
import com.tcps.pay.model.closeOrder.CloseOrderReqData;
import com.tcps.pay.model.closeOrder.CloseOrderRespData;
import com.tcps.pay.model.inquireOrder.InquireReqData;
import com.tcps.pay.model.inquireOrder.InquireRespData;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.Dom4JDriver;
import com.thoughtworks.xstream.io.xml.XmlFriendlyNameCoder;

public class CloseOrderService {
	public static CloseOrderRespData closeOrder(CloseOrderReqData requestData) {
		if (requestData != null) {
			try {
				String reqStr, respStr = null;
				reqStr = XMLHandler.objectToXMLStr(requestData);

				HttpClientUtil httpClientUtil = HttpClientUtil.getInstance();
				respStr = httpClientUtil.doPostStringEntity(Configure.INTERFACE_CLOSEORDER, reqStr);

				if (respStr != null) {
					CloseOrderRespData responseData = (CloseOrderRespData) XMLHandler.simpleXMLStrToObj(respStr, CloseOrderRespData.class);
					String return_code = responseData.getReturn_code();
					if ("SUCCESS".equals(return_code)) {
						String sign = responseData.getSign();
						Map<String, String> map = XMLHandler.XMLStrToMap(respStr);
						map.remove("sign");
						String ourSign = Signature.getSign(map);
						if (sign.equals(ourSign)) {
							return responseData;
						}
					}
				}
			} catch (Exception e) {
				// TODO: handle exception
				return null;
			}
		}
		return null;
	}
}
