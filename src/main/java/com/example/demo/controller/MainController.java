package com.example.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.example.demo.dto.InquiryDto;
import com.example.demo.dto.InquiryResultDto;
import com.example.demo.dto.InquiryResultDto2;
import com.example.demo.tcp.TCPClient;
import com.example.demo.tcp.TCPServerInfo;
import com.example.demo.util.AccountInfoParser;




@Controller
public class MainController {
	
	
	private TCPClient tcpClient;
	
	private AccountInfoParser accountInfoParser;

	@Autowired
	public void setTcpClient(TCPClient tcpClient) {
		this.tcpClient = tcpClient;
	}
	
	@Autowired
	public void setAccountInfoParser(AccountInfoParser accountInfoParser) {
		this.accountInfoParser = accountInfoParser;
	}
	
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/*************************
	 * localhost:8080/main
	 * 
	 *************************/
	@GetMapping("main")
	public String main() {
		logger.info("[ View ] http://localhost:8080/main");
		
		return "/static/start.html";
		
	}
	/*************************
	 * localhost:8080/inquiry
	 * 
	 * 
	 *************************/	
	@ResponseBody
	@PostMapping("inquiry")
	public InquiryResultDto accountInquiry(@ModelAttribute InquiryDto dto, InquiryResultDto resultDto ,InquiryResultDto2 resultDto2)  { //throws UnknownHostException, IOException
		
		// 전문 데이터 조립
		String message = accountInfoParser.attach(dto);
		
		// 전문 데이터 전송 응답
		String responseData = tcpClient.connectAndSendAndReceive(TCPServerInfo.IP, TCPServerInfo.PORT, message);
		
		// 전문 응답 데이터 저장
		accountInfoParser.interpret(responseData, resultDto, resultDto2);
		
		return resultDto;
	}
}
