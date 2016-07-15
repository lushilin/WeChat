package com.tcps.pay.model.tradeGuarantee;

public class TradeGuaranteeReqData {

	// 微信分配的公众账号ID（企业号corpid即为此appId）
	private String appid;

	// 微信支付分配的商户号
	private String mch_id;

	// 微信支付分配的终端设备号，商户自定义
	private String device_info;

	// 随机字符串，不长于32位。
	private String nonce_str;

	// 签名
	private String sign;

	// 报对应的接口的完整URL
	private String interface_url;

	// 接口耗时情况，单位为毫秒
	private int execute_time;

	// SUCCESS/FAIL 此字段是通信标识，非交易标识，交易是否成功需要查看trade_state来判断
	private String return_code;

	// 返回信息，如非空，为错误原因 签名失败 参数格式校验错误
	private String return_msg;

	// SUCCESS/FAIL
	private String result_code;

	// ORDERNOTEXIST—订单不存在 SYSTEMERROR—系统错误
	private String err_code;

	// 结果信息描述
	private String err_code_des;

	// 商户系统内部的订单号,商户可以在上报时提供相关商户订单号方便微信支付更好的提高服务质量
	private String out_trade_no;

	// 发起接口调用时的机器IP
	private String user_ip;

	// 系统时间，格式为yyyyMMddHHmmss
	private String time;

}
