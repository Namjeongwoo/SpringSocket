package com.example.demo.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dto.InquiryDto;
import com.example.demo.dto.InquiryResultDto;
import com.example.demo.dto.InquiryResultDto2;

@Service
public class AccountInfoParser {

	public AccountInfoParser() {

	}
	
	// 서버로 보낼 데이터 조립
	public String attach(InquiryDto inquiryDto) {
		String dataAttach = "";
		String repeatDataAttach = "";
		
		// 고정데이터
		String account = inquiryDto.getAccountNumber(); // 계좌번호
		String password = inquiryDto.getPassword(); // 비밀번호
		
		// Type=RO (숫자 left 0 padding)
		String inputCount = String.format("%04d", Integer.parseInt(inquiryDto.getInputCount())); // 입력건수
		

		// Type=L (문자 right ' ' padding)
		String passwordType = String.format("%-2s", inquiryDto.getPasswordType()); // 비밀번호입력매체구분
		String accountType = String.format("%-2s", inquiryDto.getAccountType()); // 계좌구분

		Integer i;
		Integer count = Integer.parseInt(inquiryDto.getInputCount());

		dataAttach = account + accountType + password + passwordType + inputCount;

		// 반복데이터
		for (i = 0; i < count; i++) {
			String eventCode = String.format("%-12s", inquiryDto.getRepeatData().get(i).getEventCode()); // 종목코드
			String sellType = String.format("%s", inquiryDto.getRepeatData().get(i).getSellType()); // 매도수구분
			String abroadOrderType = String.format("%s", inquiryDto.getRepeatData().get(i).getAbroadOrderType()); // 해외주문유형
			String orderPrice = String.format("%-20s", inquiryDto.getRepeatData().get(i).getOrderPrice()); // 주문표시가격
			String amount = String.format("%09d", Integer.parseInt(inquiryDto.getRepeatData().get(i).getAmount())); // 수량

			repeatDataAttach = repeatDataAttach + eventCode + sellType + abroadOrderType + orderPrice + amount;

		}

		return dataAttach + repeatDataAttach;
	}

	// 서버에서 받은 데이터 DTO 세팅
	public InquiryResultDto interpret(String responseData, InquiryResultDto dto, InquiryResultDto2 dto2) {
		
		Integer headerSize = 74; //헤더 사이즈
		Integer resultCountSize = 4;//출력건수 사이즈
		Integer repeatSize = 48;//반복부 사이즈
		
		ArrayList<InquiryResultDto2> list = new ArrayList<>();
		
		String bodyData = responseData.substring(headerSize, responseData.length()); //바디부
		String resultCount = bodyData.substring(0, resultCountSize); //출력건수
		String totalRepeatData = bodyData.substring(resultCountSize); //바디부-출력건수
		
		dto.setResultCount(Integer.parseInt(resultCount));
		
		
		
		// 반복데이터
		for(int i = 0; i < Integer.parseInt(resultCount); i++) {
			
			dto2 = new InquiryResultDto2();
			
			String repeatData = totalRepeatData.substring(0, repeatSize); // 총 길이 48
			
			String currencyCode = repeatData.substring(0, 3);
			
			String orderableAmount = repeatData.substring(4, 18);
			
			String evidenceAmount = repeatData.substring(19, 33);
			
			String marginBalance = repeatData.substring(34, 48);
			
			dto2.setCurrencyCode(currencyCode.trim());
			dto2.setOrderableAmount(Integer.parseInt(orderableAmount));
			dto2.setEvidenceAmonut(Integer.parseInt(evidenceAmount));
			dto2.setMarginBalance(Integer.parseInt(marginBalance));
			
			list.add(i, dto2);
			
			totalRepeatData = totalRepeatData.substring(repeatSize);
			
			if(totalRepeatData == null){  // true
				break;
			}
		}
		
		dto.setInquiryResults(list);
		
		return dto;
	}

}
