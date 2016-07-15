package com.tcps.pay.model.bill;

import com.tcps.pay.common.Configure;
import com.tcps.pay.common.PayUtill;
import com.tcps.pay.common.Signature;
                
public class BillReqData {
	// 微信分配的公众账号ID（企业号corpid即为此appId）
	private String appid;

	// 微信支付分配的商户号
	private String mch_id;

	// 微信支付分配的终端设备号
	private String device_info;

	// 随机字符串，不长于32位。推荐随机数生成算法
	private String nonce_str;

	// 签名，详见签名生成算法
	private String sign;

	// 下载对账单的日期，格式：20140603
	private String bill_date;

	// ALL，返回当日所有订单信息，默认值
	// SUCCESS，返回当日成功支付的订单
	// REFUND，返回当日退款订单
	private String bill_type;

	public BillReqData(String device_info, String bill_date, String bill_type) {
		this.appid = Configure.getAppid();
		this.mch_id = Configure.getMch_id();
		this.device_info = device_info;
		this.nonce_str = PayUtill.randomString(30);
		this.bill_date = bill_date;
		this.bill_type = bill_type;
		this.sign = Signature.getSign(PayUtill.toMap(this));
	}

	public String getDevice_info() {
		return device_info;
	}

	public void setDevice_info(String device_info) {
		this.device_info = device_info;
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

	public String getBill_date() {
		return bill_date;
	}

	public void setBill_date(String bill_date) {
		this.bill_date = bill_date;
	}

	public String getBill_type() {
		return bill_type;
	}

	public void setBill_type(String bill_type) {
		this.bill_type = bill_type;
	}

}
