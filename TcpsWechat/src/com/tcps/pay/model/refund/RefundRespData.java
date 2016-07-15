package com.tcps.pay.model.refund;

public class RefundRespData {
	// SUCCESS/FAIL
	private String return_code;

	// 返回信息，如非空，为错误原因 签名失败 参数格式校验错误
	private String return_msg;

	// SUCCESS/FAIL SUCCESS退款申请接收成功，结果通过退款查询接口查询 FAIL 提交业务失败
	private String result_code;

	// 列表详见错误码列表
	private String err_code;

	// 结果信息描述
	private String err_code_des;

	// 微信分配的公众账号ID
	private String appid;

	// 微信支付分配的商户号
	private String mch_id;

	// 微信支付分配的终端设备号，与下单一致
	private String device_info;

	// 随机字符串，不长于32位
	private String nonce_str;

	// 签名，详见签名算法
	private String sign;

	// 微信订单号
	private String transaction_id;

	// 商户系统内部的订单号
	private String out_trade_no;

	// 商户退款单号
	private String out_refund_no;

	// 微信退款单号
	private String refund_id;

	// ORIGINAL—原路退款 BALANCE—退回到余额
	private String refund_channel;

	// 退款总金额,单位为分,可以做部分退款
	private int refund_fee;

	// 退款金额=申请退款金额-非充值代金券退款金额，退款金额<=申请退款金额
	private int settlement_refund_fee_$n;

	// 订单总金额，单位为分，只能为整数，详见支付金额
	private int total_fee;

	// 应结订单金额=订单金额-非充值代金券金额，应结订单金额<=订单金额。
	private int settlement_total_fee;

	// 订单金额货币类型，符合ISO 4217标准的三位字母代码，默认人民币：CNY，其他值列表详见货币类型
	private String fee_type;

	// 现金支付金额，单位为分，只能为整数，详见支付金额
	private int cash_fee;

	// 现金退款金额，单位为分，只能为整数，详见支付金额
	private int cash_refund_fee;

	/*
	 * 代金券暂时不用 //CASH--充值代金券 NO_CASH---非充值代金券
	 * 订单使用代金券时有返回（取值：CASH、NO_CASH）。$n为下标,从0开始编号，举例：coupon_type_$0 private
	 * String coupon_type_$n;
	 * 
	 * //代金券退款金额<=退款金额，退款金额-代金券或立减优惠退款金额为现金，说明详见代金券或立减优惠 private int
	 * coupon_refund_fee_$n;
	 * 
	 * //退款代金券使用数量 ,$n为下标,从0开始编号 private int coupon_refund_count_$n;
	 * 
	 * //退款代金券批次ID ,$n为下标，$m为下标，从0开始编号 private String
	 * coupon_refund_batch_id_$n_$m;
	 * 
	 * //退款代金券ID, $n为下标，$m为下标，从0开始编号 private String coupon_refund_id_$n_$m;
	 * 
	 * //单个退款代金券支付金额, $n为下标，$m为下标，从0开始编号 private int coupon_refund_fee_$n_$m;
	 */
}
