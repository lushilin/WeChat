package com.tcps.java.common.model;

public class User {
	private String openId;
	private String cityCode;
	private String cardId;
	public User(String openId, String cardId, String cityCode){
		this.openId = openId;
		this.cardId = cardId;
		this.cityCode = cityCode;
	}
	
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public String getCityCode() {
		return cityCode;
	}
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
	public String getCardId() {
		return cardId;
	}
	public void setCardId(String cardId) {
		this.cardId = cardId;
	}
	
}
