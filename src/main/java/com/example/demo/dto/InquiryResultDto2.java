package com.example.demo.dto;

public class InquiryResultDto2 {

	private String currencyCode;  //통화코드
	
	private Integer orderableAmount; //주문가능금액주문가능총액
	
	private Integer evidenceAmonut;  //소요증거금위탁증거금
	
	private Integer marginBalance;  //잔액추가증거금
	
	
	
	
	public InquiryResultDto2() {
		super();
	}

	public InquiryResultDto2(String currencyCode, Integer orderableAmount, Integer evidenceAmonut, Integer marginBalance) {
		super();
		this.currencyCode = currencyCode;
		this.orderableAmount = orderableAmount;
		this.evidenceAmonut = evidenceAmonut;
		this.marginBalance = marginBalance;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public Integer getOrderableAmount() {
		return orderableAmount;
	}

	public void setOrderableAmount(Integer orderableAmount) {
		this.orderableAmount = orderableAmount;
	}

	public Integer getEvidenceAmonut() {
		return evidenceAmonut;
	}

	public void setEvidenceAmonut(Integer evidenceAmonut) {
		this.evidenceAmonut = evidenceAmonut;
	}

	public Integer getMarginBalance() {
		return marginBalance;
	}

	public void setMarginBalance(Integer marginBalance) {
		this.marginBalance = marginBalance;
	}
	
	
}
