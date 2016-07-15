package com.tcps.pay.model.goods;

import com.tcps.pay.common.PayUtill;

public class Goods {
	//product_id对应的body
	private String[] bodies = {"转账10元","转账20元","转账50元","转账100元","转账200元","转账300元"};
	
	//product_id对应的details
	private String[] details = {"通卡公司","通卡公司","通卡公司","通卡公司","通卡公司","通卡公司"};
	
	//product_id对应的body
	private int[] uniteFees = {1000,2000,5000,10000,20000,30000};
	
	//product_id对应的goodtags
	private String[] goodTags = {"分为单位","分为单位","分为单位","分为单位","分为单位","分为单位"};
	
	// 商品或支付单简要描述
	private String body;

	// 商品名称明细列表
	private String detail;

	// 订单单位金额，单位为分
	private int unit_fee;

	// 商品标记，代金券或立减优惠功能的参数
	private String goods_tag;

	// trade_type=NATIVE，此参数必传。此id为二维码中包含的商品ID，商户自行定义
	private String product_id;

	// no_credit--指定不能使用信用卡支付
	private String limit_pay;

	public Goods(String product_id) {
		int id = 0;
		switch (product_id) {
		case "0001":
			id = 1;
			break;
		case "0002":
			id = 2;
			break;
		case "0003":
			id = 3;
			break;
		case "0004":
			id = 4;
			break;
		case "0005":
			id = 5;
			break;
		case "0006":
			id = 6;
			break;
		default:
			break;
		}
		this.body = bodies[id-1];
		this.detail = details[id-1];
		this.unit_fee = uniteFees[id-1];
		this.unit_fee = 1;
		this.goods_tag = goodTags[id-1];
		this.product_id = product_id;
		this.limit_pay = null;
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

	public int getUnit_fee() {
		return unit_fee;
	}

	public void setUnit_fee(int unit_fee) {
		this.unit_fee = unit_fee;
	}

	public String getGoods_tag() {
		return goods_tag;
	}

	public void setGoods_tag(String goods_tag) {
		this.goods_tag = goods_tag;
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

}
