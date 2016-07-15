package com.tcps.pay.model.inquireOrder;

public class InquireRespData {
	// SUCCESS/FAIL
	// 此字段是通信标识，非交易标识，交易是否成功需要查看trade_state来判断
	private String return_code;

	// 返回信息，如非空，为错误原因
	// 签名失败or参数格式校验错误
	private String return_msg;

	// 以下字段在return_code为SUCCESS的时候有返回
	// 微信分配的公众账号ID
	private String appid;

	// 微信支付分配的商户号
	private String mch_id;

	// 随机字符串，不长于32位
	private String nonce_str;

	// 签名
	private String sign;

	// 业务结果SUCCESS/FAIL
	private String result_code;

	// 错误码
	private String err_code;

	// 结果信息描述
	private String err_code_des;

	// 以下字段在return_code 和result_code都为SUCCESS的时候有返回
	// 微信支付分配的终端设备号，
	private String device_info;

	// 用户在商户appid下的唯一标识
	private String openid;

	// 用户是否关注公众账号，Y-关注，N-未关注，仅在公众账号类型支付有效
	private String is_subscribe;

	// 调用接口提交的交易类型，取值如下：JSAPI，NATIVE，APP，MICROPAY
	private String trade_type;

	// SUCCESS—支付成功 REFUND—转入退款 NOTPAY—未支付 CLOSED—已关闭 REVOKED—已撤销（刷卡支付）
	// USERPAYING--用户支付中 PAYERROR--支付失败
	private String trade_state;

	// 银行类型，采用字符串类型的银行标识
	private String bank_type;

	// 订单总金额，单位为分
	private int total_fee;

	// 应结订单金额=订单金额-非充值代金券金额，应结订单金额<=订单金额
	private int settlement_total_fee;

	// 货币类型，符合ISO 4217标准的三位字母代码，默认人民币：CNY
	private String fee_type;

	// 现金支付金额
	private int cash_fee;

	// 货币类型，符合ISO 4217标准的三位字母代码，默认人民币：CNY
	private String cash_fee_type;
	/*
	 * 不适用代金券，所以取消以下属性 private String coupon_fee; private String coupon_count;
	 * private String coupon_batch_id_$n; private String coupon_type_$n; private
	 * String coupon_id_$n; private String coupon_fee_$n;
	 */
	// 微信支付订单号
	private String transaction_id;

	// 商户系统的订单号，与请求一致
	private String out_trade_no;

	// 附加数据，原样返回
	private String attach;

	// 订单支付时间，格式为yyyyMMddHHmmss，如2009年12月25日9点10分10秒表示为20091225091010
	private String time_end;

	// 对当前查询订单状态的描述和下一步操作的指引
	private String trade_state_desc;

	public String getReturn_code() {
		return return_code;
	}

	public void setReturn_code(String return_code) {
		this.return_code = return_code;
	}

	public String getReturn_msg() {
		return return_msg;
	}

	public void setReturn_msg(String return_msg) {
		this.return_msg = return_msg;
	}

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getMch_id() {
		return mch_id;
	}

	public void setMch_id(String mch_id) {
		this.mch_id = mch_id;
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

	public String getResult_code() {
		return result_code;
	}

	public void setResult_code(String result_code) {
		this.result_code = result_code;
	}

	public String getErr_code() {
		return err_code;
	}

	public void setErr_code(String err_code) {
		this.err_code = err_code;
	}

	public String getErr_code_des() {
		return err_code_des;
	}

	public void setErr_code_des(String err_code_des) {
		this.err_code_des = err_code_des;
	}

	public String getDevice_info() {
		return device_info;
	}

	public void setDevice_info(String device_info) {
		this.device_info = device_info;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getIs_subscribe() {
		return is_subscribe;
	}

	public void setIs_subscribe(String is_subscribe) {
		this.is_subscribe = is_subscribe;
	}

	public String getTrade_type() {
		return trade_type;
	}

	public void setTrade_type(String trade_type) {
		this.trade_type = trade_type;
	}

	public String getTrade_state() {
		return trade_state;
	}

	public void setTrade_state(String trade_state) {
		this.trade_state = trade_state;
	}

	public String getBank_type() {
		return bank_type;
	}

	public void setBank_type(String bank_type) {
		this.bank_type = bank_type;
	}

	public int getTotal_fee() {
		return total_fee;
	}

	public void setTotal_fee(int total_fee) {
		this.total_fee = total_fee;
	}

	public int getSettlement_total_fee() {
		return settlement_total_fee;
	}

	public void setSettlement_total_fee(int settlement_total_fee) {
		this.settlement_total_fee = settlement_total_fee;
	}

	public String getFee_type() {
		return fee_type;
	}

	public void setFee_type(String fee_type) {
		this.fee_type = fee_type;
	}

	public int getCash_fee() {
		return cash_fee;
	}

	public void setCash_fee(int cash_fee) {
		this.cash_fee = cash_fee;
	}

	public String getCash_fee_type() {
		return cash_fee_type;
	}

	public void setCash_fee_type(String cash_fee_type) {
		this.cash_fee_type = cash_fee_type;
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

	public String getAttach() {
		return attach;
	}

	public void setAttach(String attach) {
		this.attach = attach;
	}

	public String getTime_end() {
		return time_end;
	}

	public void setTime_end(String time_end) {
		this.time_end = time_end;
	}

	public String getTrade_state_desc() {
		return trade_state_desc;
	}

	public void setTrade_state_desc(String trade_state_desc) {
		this.trade_state_desc = trade_state_desc;
	}

}
