package com.tcps.pay.model.closeOrder;

import java.util.HashMap;
import java.util.Map;

import com.tcps.pay.common.Configure;
import com.tcps.pay.common.PayUtill;
import com.tcps.pay.common.Signature;

public class CloseOrderReqData {
	// 微信分配的公众账号ID
	private String appid;

	// 微信支付分配的商户号
	private String mch_id;

	// 商户系统内部的订单号
	private String out_trade_no;

	// 商户系统内部的订单号,32个字符内
	private String nonce_str;

	// 签名
	private String sign;

	public CloseOrderReqData(String out_trade_no) {
		this.appid = Configure.getAppid();
		this.mch_id = Configure.getMch_id();
		this.out_trade_no = out_trade_no;
		this.nonce_str = PayUtill.randomString(32);

		Map<String, String> map = new HashMap<String, String>();
		map.put("appid", this.appid);
		map.put("mch_id", this.mch_id);
		map.put("out_trade_no", out_trade_no);
		map.put("nonce_str", this.nonce_str);
		String mySign = Signature.getSign(map);

		this.sign = mySign;
	}

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}

	public String getNonce_str() {
		return nonce_str;
	}

	public void setNonce_str(String nonce_str) {
		this.nonce_str = nonce_str;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

}
