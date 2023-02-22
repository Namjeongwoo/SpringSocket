package com.example.demo.dto;

import java.util.ArrayList;

public class InquiryResultDto {

	private Integer resultCount; //출력건수
	
	private ArrayList<InquiryResultDto2> inquiryResults;	
	
	
	public InquiryResultDto() {
	
	}
	
	public InquiryResultDto(Integer resultCount, ArrayList<InquiryResultDto2> inquiryResults) {
		super();
		this.resultCount = resultCount;
		this.inquiryResults = inquiryResults;
	}
	
	

	@Override
	public String toString() {
		return "InquiryResultDto [resultCount=" + resultCount + ", inquiryResults=" + inquiryResults
				+ ", getResultCount()=" + getResultCount() + ", getInquiryResults()=" + getInquiryResults()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}

	public Integer getResultCount() {
		return resultCount;
	}

	public void setResultCount(Integer resultCount) {
		this.resultCount = resultCount;
	}

	public ArrayList<InquiryResultDto2> getInquiryResults() {
		return inquiryResults;
	}

	public void setInquiryResults(ArrayList<InquiryResultDto2> inquiryResults) {
		this.inquiryResults = inquiryResults;
	}
	
}
