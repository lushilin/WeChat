package com.tcps.pay.model.refund;

import com.tcps.pay.common.Configure;
import com.tcps.pay.common.PayUtill;
import com.tcps.pay.common.Signature;
                
public class RefundReqData {
	// 微信分配的公众账号ID
	private String appid;

	// 微信支付分配的商户号
	private String mch_id;

	// 终端设备号
	private String device_info;

	// 随机字符串，不长于32位
	private String nonce_str;

	// 签名
	private String sign;

	// 微信生成的订单号，在支付通知中有返回
	private String transaction_id;

	// 商户侧传给微信的订单号
	private String out_trade_no;

	// 商户系统内部的退款单号，商户系统内部唯一，同一退款单号多次请求只退一笔
	private String out_refund_no;

	// 订单总金额，单位为分，只能为整数
	private int total_fee;

	// 退款总金额，订单总金额，单位为分，只能为整数
	private int refund_fee;

	// 货币类型，符合ISO 4217标准的三位字母代码，默认人民币：CNY
	private String refund_fee_type;

	// 操作员帐号, 默认为商户号
	private String op_user_id;

	public RefundReqData(String device_info, String transaction_id,
			String out_trade_no, String out_refund_no, int total_fee,
			int refund_fee, String op_user_id) {
		this.appid = Configure.getAppid();
		this.mch_id = Configure.getMch_id();
		this.device_info = device_info;
		this.nonce_str = PayUtill.randomString(30);
		this.transaction_id = transaction_id;
		this.out_trade_no = out_trade_no;
		this.out_refund_no = out_refund_no;
		this.total_fee = total_fee;
		this.refund_fee = refund_fee;
		this.refund_fee_type = "CNY";
		this.op_user_id = op_user_id;
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

	public int getTotal_fee() {
		return total_fee;
	}

	public void setTotal_fee(int total_fee) {
		this.total_fee = total_fee;
	}

	public int getRefund_fee() {
		return refund_fee;
	}

	public void setRefund_fee(int refund_fee) {
		this.refund_fee = refund_fee;
	}

	public String getRefund_fee_type() {
		return refund_fee_type;
	}

	public void setRefund_fee_type(String refund_fee_type) {
		this.refund_fee_type = refund_fee_type;
	}

	public String getOp_user_id() {
		return op_user_id;
	}

	public void setOp_user_id(String op_user_id) {
		this.op_user_id = op_user_id;
	}

}
