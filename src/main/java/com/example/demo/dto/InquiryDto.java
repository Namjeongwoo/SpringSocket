package com.example.demo.dto;

import java.util.List;

public class InquiryDto {
	
	private String accountNumber; //계좌번호

	private String accountType; //계좌구분
	
	private String  password; //비밀번호
	
	private String passwordType; //비밀번호입력매체구분
	
	private String inputCount; //입력건수
	
	private List<InquiryDto2> repeatData;
	
	public InquiryDto() {
		
	}
	
	
	

	public InquiryDto(String accountNumber, String accountType, String password, String passwordType, String inputCount, List<InquiryDto2> repeatData) {
		super();
		this.accountNumber = accountNumber;
		this.accountType = accountType;
		this.password = password;
		this.passwordType = passwordType;
		this.inputCount = inputCount;
		this.repeatData = repeatData;
	}


	@Override
	public String toString() {
		return "InquiryDto [accountNumber=" + accountNumber + ", accountType=" + accountType + ", password=" + password
				+ ", passwordType=" + passwordType + ", inputCount=" + inputCount + ", repeatData=" + repeatData
				+ ", getAccountNumber()=" + getAccountNumber() + ", getAccountType()=" + getAccountType()
				+ ", getPassword()=" + getPassword() + ", getPasswordType()=" + getPasswordType() + ", getInputCount()="
				+ getInputCount() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}




	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordType() {
		return passwordType;
	}

	public void setPasswordType(String passwordType) {
		this.passwordType = passwordType;
	}

	public String getInputCount() {
		return inputCount;
	}

	public void setInputCount(String inputCount) {
		this.inputCount = inputCount;
	}

	public List<InquiryDto2> getRepeatData() {
		return repeatData;
	}

	public void setRepeatData(List<InquiryDto2> repeatData) {
		this.repeatData = repeatData;
	}

	


	
}
