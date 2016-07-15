package com.tcps.pay.model.order;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.tcps.pay.common.Configure;
import com.tcps.pay.common.PayUtill;
import com.tcps.pay.common.Signature;
import com.tcps.pay.model.goods.Goods;

public class OrderReqData {
	public static final String NOTIFY_URL = "http://www.yanwenxiong.cn/TcpsWechat/notify";
	
	// 微信分配的公众账号ID
	private String appid;

	// 微信支付分配的商户号
	private String mch_id;

	// PC网页或公众号内支付请传"WEB"
	private String device_info;

	// 随机字符串，不长于32位
	private String nonce_str;

	// 签名
	private String sign;

	// 商品或支付单简要描述
	private String body;

	// 商品名称明细列表
	private String detail;

	// 附加数据，在查询API和支付通知中原样返回，该字段主要用于商户携带订单的自定义数据
	private String attach;

	// 商户系统内部的订单号,32个字符内、可包含字母
	private String out_trade_no;

	// 符合ISO 4217标准的三位字母代码，默认人民币：CNY
	private String fee_type;

	// 订单总金额，单位为分
	private int total_fee;

	// APP和网页支付提交用户端ip
	private String spbill_create_ip;

	// 订单生成时间，格式为yyyyMMddHHmmss，如2009年12月25日9点10分10秒表示为20091225091010
	private String time_start;

	// 订单失效时间，格式为yyyyMMddHHmmss，如2009年12月27日9点10分10秒表示为20091227091010
	private String time_expire;

	// 商品标记，代金券或立减优惠功能的参数
	private String goods_tag;

	// 接收微信支付异步通知回调地址，通知url必须为直接可访问的url，不能携带参数。
	private String notify_url;

	// 取值如下：JSAPI，NATIVE，APP，
	private String trade_type;

	// trade_type=NATIVE，此参数必传。此id为二维码中包含的商品ID，商户自行定义。
	private String product_id;

	// no_credit--指定不能使用信用卡支付
	private String limit_pay;

	// trade_type=JSAPI，此参数必传，用户在商户appid下的唯一标识
	private String openid;

	public OrderReqData(Goods good, int number, String openid, String attach,
			String out_trade_no, String ip) {
		this.appid = Configure.getAppid();
		this.mch_id = Configure.getMch_id();
		this.device_info = "WEB";
		this.nonce_str = PayUtill.randomString(32);
		this.body = good.getBody();
		this.detail = good.getDetail();
		this.attach = attach;
		this.out_trade_no = out_trade_no;
		this.fee_type = "CNY";
		this.total_fee = good.getUnit_fee() * number;
		this.spbill_create_ip = ip;
		Date nowDate = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyymmddhhmmss");
		this.time_start = sdf.format(nowDate);
		// TODO 订单失效的时间是不得而知的，不知道怎么填写。
		// this.time_expire = good.getTime_expire();
		this.goods_tag = good.getGoods_tag();
		this.notify_url = NOTIFY_URL;
		this.trade_type = "JSAPI";
		this.product_id = good.getProduct_id();
		this.limit_pay = good.getLimit_pay();
		this.openid = openid;

		Map<String, String> map = new HashMap<String, String>();
		map.put("appid", this.getAppid());
		map.put("mch_id", this.getMch_id());
		map.put("device_info", this.getDevice_info());
		map.put("nonce_str", this.getNonce_str());
		map.put("body", this.getBody());
		map.put("detail", this.getDetail());
		map.put("attach", this.getAttach());
		map.put("out_trade_no", this.getOut_trade_no());
		map.put("fee_type", this.getFee_type());
		map.put("total_fee", String.valueOf(this.getTotal_fee()));
		map.put("spbill_create_ip", ip);
		map.put("time_start", this.getTime_start());
		map.put("time_expire", this.getTime_expire());
		map.put("goods_tag", this.getGoods_tag());
		map.put("notify_url", this.getNotify_url());
		map.put("trade_type", this.getTrade_type());
		map.put("product_id", this.getProduct_id());
		map.put("limit_pay", this.getLimit_pay());
		map.put("openid", this.getOpenid());
		String mySign = Signature.getSign(map);
		this.sign = mySign;
	}

	/**
	 * 产生商家内部的订单号
	 * 
	 * @return String:前18位为随机字符串后14位为yyyymmddhhmmss的现在时间格式。
	 */
	public static String generateOutTradeNo() {
		String left = PayUtill.randomString(18);
		String right = PayUtill.getNowFormalTime();
		return (left + right);
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

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getAttach() {
		return attach;
	}

	public void setAttach(String attach) {
		this.attach = attach;
	}

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}

	public String getFee_type() {
		return fee_type;
	}

	public void setFee_type(String fee_type) {
		this.fee_type = fee_type;
	}

	public int getTotal_fee() {
		return total_fee;
	}

	public void setTotal_fee(int total_fee) {
		this.total_fee = total_fee;
	}

	public String getSpbill_create_ip() {
		return spbill_create_ip;
	}

	public void setSpbill_create_ip(String spbill_create_ip) {
		this.spbill_create_ip = spbill_create_ip;
	}

	public String getTime_start() {
		return time_start;
	}

	public void setTime_start(String time_start) {
		this.time_start = time_start;
	}

	public String getTime_expire() {
		return time_expire;
	}

	public void setTime_expire(String time_expire) {
		this.time_expire = time_expire;
	}

	public String getGoods_tag() {
		return goods_tag;
	}

	public void setGoods_tag(String goods_tag) {
		this.goods_tag = goods_tag;
	}

	public String getNotify_url() {
		return notify_url;
	}

	public void setNotify_url(String notify_url) {
		this.notify_url = notify_url;
	}

	public String getTrade_type() {
		return trade_type;
	}

	public void setTrade_type(String trade_type) {
		this.trade_type = trade_type;
	}

	public String getProduct_id() {
		return product_id;
	}

	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}

	public String getLimit_pay() {
		return limit_pay;
	}

	public void setLimit_pay(String limit_pay) {
		this.limit_pay = limit_pay;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

}
