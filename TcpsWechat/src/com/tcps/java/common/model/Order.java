package com.tcps.java.common.model;

public class Order {
	//10位时间+1位类型（奇数）+5位序列
		private String orderNo;
		//创建订单时间
		private String orderTime;
		//订单状态 0：未付款 1：已付款 2:已完成充值 9：取消订单 7：已退款
		private int is_Ok;
		//充值金额
		private int orderMoney;
		//手续费
		private double poundageMoney;
		//总金额
		private double sumMoney;
		//卡号
		private String cardId;
		//用户
		private String userId;
		//0：未结转 1：结转
		private int balanceFlag;
		//支付类型
		private int payType;
		//0：未到警戒风控 1：警戒风控
		private int warnType;
		
	
	public Order(String orderNo, String orderTime, int is_Ok,
				int orderMoney, double poundageMoney, double sumMoney,
				String cardId, String userId, int balanceFlag, int payType,
				int warnType) {
			super();
			this.orderNo = orderNo;
			this.orderTime = orderTime;
			this.is_Ok = is_Ok;
			this.orderMoney = orderMoney;
			this.poundageMoney = poundageMoney;
			this.sumMoney = sumMoney;
			this.cardId = cardId;
			this.userId = userId;
			this.balanceFlag = balanceFlag;
			this.payType = payType;
			this.warnType = warnType;
		}
	
	
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}
	public int getIs_Ok() {
		return is_Ok;
	}
	public void setIs_Ok(int is_Ok) {
		this.is_Ok = is_Ok;
	}
	public int getOrderMoney() {
		return orderMoney;
	}
	public void setOrderMoney(int orderMoney) {
		this.orderMoney = orderMoney;
	}
	public double getPoundageMoney() {
		return poundageMoney;
	}
	public void setPoundageMoney(int poundageMoney) {
		this.poundageMoney = poundageMoney;
	}
	public double getSumMoney() {
		return sumMoney;
	}
	public void setSumMoney(int sumMoney) {
		this.sumMoney = sumMoney;
	}
	public String getCardId() {
		return cardId;
	}
	public void setCardId(String cardId) {
		this.cardId = cardId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getBalanceFlag() {
		return balanceFlag;
	}
	public void setBalanceFlag(int balanceFlag) {
		this.balanceFlag = balanceFlag;
	}
	public int getPayType() {
		return payType;
	}
	public void setPayType(int payType) {
		this.payType = payType;
	}
	public int getWarnType() {
		return warnType;
	}
	public void setWarnType(int warnType) {
		this.warnType = warnType;
	}
	
}
