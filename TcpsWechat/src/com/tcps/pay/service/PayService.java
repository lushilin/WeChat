package com.tcps.pay.service;

import java.text.DecimalFormat;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletException;

import org.springframework.stereotype.Service;

import com.tcps.java.common.dao.IBaseDAO;
import com.tcps.pay.common.Configure;
import com.tcps.pay.common.HttpClientUtil;
import com.tcps.pay.common.Signature;
import com.tcps.pay.common.XMLHandler;
import com.tcps.pay.model.order.OrderReqData;
import com.tcps.pay.model.order.OrderRespData;


@Service(value="Pay")
public class PayService {
	@Resource(name = "jdbcDAO")
	private IBaseDAO baseDAO;
	
	public IBaseDAO getDAO(){
		return baseDAO;
	}
	
	public void createOrder(String order_no,String transaction_id,int sum_money,
			String return_url,String notify_url,String card_no,
			String pri_param,String create_time,int trade_state,
			String trade_time,int check_over) throws Exception {
		double sumMoney = (double)sum_money / 100;
		DecimalFormat df = new DecimalFormat("0.00");
		df.format(sumMoney);
		
		String sql = "insert into wechat_pay_info(order_no,transaction_id,sum_money,return_url,notify_url,card_no,pri_param,create_time,trade_state,trade_time,check_over) "
				+ "values('" + order_no + "','" 
				+ transaction_id + "'," 
				+ Double.toString(sumMoney) + ",'" 
				+ return_url + "','"
				+ notify_url + "','"
				+ card_no + "','" 
				+ pri_param + "',to_date('" 
				+ create_time + "','yyyy-MM-dd HH24:mi:ss')," 
				+ trade_state +  ",to_date('"
				+ trade_time + "','yyyy-MM-dd HH24:mi:ss')," 
				+ check_over + ")";
		System.out.println("记录订单支付情况的sql语句： " + sql);
		getDAO().update(sql);
	}
	
	public void updatePayStatus(String order_no){
		String sql = "update wechat_pay_info set "
				+ "trade_state=1 "
				+"where order_no='" + order_no + "'";
		try {
			getDAO().update(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 执行调用微信支付的业务逻辑
	 * 
	 * @param OrderReqData
	 * @throws ServletException
	 */
	public OrderRespData run(OrderReqData requestData) {
		if (requestData != null) {
			try {
				String reqStr, respStr = null;
				reqStr = XMLHandler.objectToXMLStr(requestData);
				HttpClientUtil httpClientUtil = HttpClientUtil.getInstance();
				respStr = httpClientUtil.doPostStringEntity(Configure.INTERFACE_ORDER, reqStr);
				if (respStr != null) {
					OrderRespData responseData = (OrderRespData) XMLHandler.simpleXMLStrToObj(respStr, OrderRespData.class);
					//签名认证，签名认证失败的将返回null。
					String sign = responseData.getSign();
					Map<String, String> map = XMLHandler.XMLStrToMap(respStr);
					map.remove("sign");
					String ourSign = Signature.getSign(map);
					if (sign.equals(ourSign)) {
						return responseData;
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
