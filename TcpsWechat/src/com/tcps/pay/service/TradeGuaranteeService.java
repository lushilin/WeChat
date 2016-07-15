package com.tcps.pay.service;

import com.tcps.pay.common.Configure;
import com.tcps.pay.common.HttpClientUtil;
import com.tcps.pay.common.XMLHandler;
import com.tcps.pay.model.tradeGuarantee.TradeGuaranteeReqData;
import com.tcps.pay.model.tradeGuarantee.TradeGuaranteeRespData;

public class TradeGuaranteeService {

	public static TradeGuaranteeRespData tradeGuarantee(TradeGuaranteeReqData requestData){
		if(requestData != null){
			try{
				String reqStr, respStr = null;
				reqStr = XMLHandler.objectToXMLStr(requestData);
				
				HttpClientUtil httpClientUtil = HttpClientUtil.getInstance();
				respStr = httpClientUtil.doPostStringEntity(Configure.INTERFACE_TRADEGUARANTEE, reqStr);
				
				if (respStr != null) {
					TradeGuaranteeRespData responseData = (TradeGuaranteeRespData) XMLHandler.simpleXMLStrToObj(respStr, TradeGuaranteeRespData.class);
					return responseData;
				}
			}catch (Exception e) {
				// TODO: handle exception
				return null;
			}
		}
		return null;
	}
}
