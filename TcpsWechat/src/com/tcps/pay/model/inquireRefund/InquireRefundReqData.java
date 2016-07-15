package com.tcps.pay.model.inquireRefund;

import com.tcps.pay.common.Configure;
import com.tcps.pay.common.PayUtill;
import com.tcps.pay.common.Signature;
                
public class InquireRefundReqData {
	// 微信分配的公众账号ID（企业号corpid即为此appId）
	private String appid;

	// 微信支付分配的商户号
	private String mch_id;

	// 商户自定义的终端设备号，如门店编号、设备的ID等
	private String device_info;

	// 随机字符串，不长于32位。推荐随机数生成算法
	private String nonce_str;

	// 签名，详见签名生成算法
	private String sign;

	// 微信订单号
	private String transaction_id;

	// 商户系统内部的订单号
	private String out_trade_no;

	// 商户侧传给微信的退款单号
	private String out_refund_no;

	// 微信生成的退款单号，在申请退款接口有返回
	private String refund_id;

	public InquireRefundReqData(String device_info, String transaction_id,
			String out_trade_no, String out_refund_no, String refund_id) {
		this.appid = Configure.getAppid();
		this.mch_id = Configure.getMch_id();
		this.device_info = device_info;
		this.nonce_str = PayUtill.randomString(30);
		this.transaction_id = transaction_id;
		this.out_trade_no = out_trade_no;
		this.out_refund_no = out_refund_no;
		this.refund_id = refund_id;
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

	public String getTransaction_id() {
		return transaction_id;
	}

	public void setTransaction_id(String transaction_id) {
		this.transaction_id = transaction_id;
	}

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}

	public String getOut_refund_no() {
		return out_refund_no;
	}

	public void setOut_refund_no(String out_refund_no) {
		this.out_refund_no = out_refund_no;
	}

	public String getRefund_id() {
		return refund_id;
	}

	public void setRefund_id(String refund_id) {
		this.refund_id = refund_id;
	}

}
