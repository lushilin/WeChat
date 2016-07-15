package com.tcps.pay.model.notifyResult;

public class NotifyResultReqData {
	// SUCCESS/FAIL
	// 此字段是通信标识，非交易标识，交易是否成功需要查看result_code来判断
	private String return_code;

	// 返回信息，如非空，为错误原因
	// 签名失败or参数格式校验错误
	private String return_msg;

	// 以下字段在return_code为SUCCESS的时候有返回
	// 微信分配的公众账号ID
	private String appid;

	// 微信支付分配的商户号
	private String mch_id;

	// 微信支付分配的终端设备号，
	private String device_info;

	// 随机字符串，不长于32位
	private String nonce_str;

	// 签名
	private String sign;

	// SUCCESS/FAIL
	private String result_code;

	// 错误
	private String err_code;

	// 错误返回的信息描述
	private String err_code_des;

	// 用户在商户appid下的唯一标识
	private String openid;

	// 用户是否关注公众账号，Y-关注，N-未关注
	private String is_subscribe;

	// JSAPI、NATIVE、APP
	private String trade_type;

	// 银行类型
	private String bank_type;

	// 订单总金额，单位为分
	private String total_fee;

	// 应结订单金额=订单金额-非充值代金券金额，应结订单金额<=订单金额
	private String settlement_total_fee;

	// 货币类型，符合ISO4217标准的三位字母代码，默认人民币：CNY
	private String fee_type;

	// 现金支付金额订单现金支付金额
	private String cash_fee;

	// 货币类型，符合ISO4217标准的三位字母代码，默认人民币：CNY
	private String cash_fee_type;

	// 代金券金额<=订单金额，订单金额-代金券金额=现金支付金额，
	private String coupon_fee;

	// 代金券使用数量
	private String coupon_count;

	// CASH--充值代金券 NO_CASH---非充值代金券 订单使用代金券时有返回（取值：CASH、NO_CASH）。
	private String coupon_type_$n;

	// 代金券ID,$n为下标
	private String coupon_id_$n;

	// 单个代金券支付金额,$n为下标，从0开始编号
	private String coupon_fee_$n;

	// 微信支付订单号
	private String transaction_id;

	// 商户系统的订单号，与请求一致。
	private String out_trade_no;

	// 商家数据包，原样返回
	private String attach;

	// 支付完成时间
	private String time_end;

}
