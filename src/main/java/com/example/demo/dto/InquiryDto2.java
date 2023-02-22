package com.example.demo.dto;

public class InquiryDto2 {
	

	private String eventCode;	//종목코드

	private String sellType;	//매도수구분

	private String abroadOrderType;	//해외주문유형

	private String orderPrice;	//주문표시가격

	private String amount;	//수량
	
	public InquiryDto2() {
		
	}
	

	public InquiryDto2(String accountNumber, String password, String accountType, String passwordType,
			String inputCount, String eventCode, String sellType, String abroadOrderType, String orderPrice,
			String amount) {
		super();
		this.eventCode = eventCode;
		this.sellType = sellType;
		this.abroadOrderType = abroadOrderType;
		this.orderPrice = orderPrice;
		this.amount = amount;
	}

	

	@Override
	public String toString() {
		return "InquiryDto2 [eventCode=" + eventCode + ", sellType=" + sellType + ", abroadOrderType=" + abroadOrderType
				+ ", orderPrice=" + orderPrice + ", amount=" + amount + ", getEventCode()=" + getEventCode()
				+ ", getSellType()=" + getSellType() + ", getAbroadOrderType()=" + getAbroadOrderType()
				+ ", getOrderPrice()=" + getOrderPrice() + ", getAmount()=" + getAmount() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}


	public String getEventCode() {
		return eventCode;
	}

	public void setEventCode(String eventCode) {
		this.eventCode = eventCode;
	}

	public String getSellType() {
		return sellType;
	}

	public void setSellType(String sellType) {
		this.sellType = sellType;
	}

	public String getAbroadOrderType() {
		return abroadOrderType;
	}

	public void setAbroadOrderType(String abroadOrderType) {
		this.abroadOrderType = abroadOrderType;
	}

	public String getOrderPrice() {
		return orderPrice;
	}

	public void setOrderPrice(String orderPrice) {
		this.orderPrice = orderPrice;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}
	
}
