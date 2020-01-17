package com.benwon.shop.controller.rest;

import java.util.HashMap;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.benwon.shop.bo.PhoneVerfyCodeEvent;
import com.benwon.shop.bo.PhoneVerfyCodeSendEvent;
import com.benwon.shop.entity.SmsRecord;
import com.benwon.shop.service.SmsService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api")
public class PhoneRestController {

	private final SmsService smsService;

	/**
	 * 發送驗證碼
	 * 
	 * @param phoneVerfyCodeSendEvent
	 * @return
	 */
	@PostMapping(path = "/v1/phoneVerfyCodeSend")
	public HashMap verfyCodeSend(@RequestBody PhoneVerfyCodeSendEvent phoneVerfyCodeSendEvent) {
		SmsRecord smsRecord = smsService.send(phoneVerfyCodeSendEvent);
		HashMap reObj = new HashMap();
		reObj.put("smsId", smsRecord.getId());
		return reObj;
	}

	@PostMapping(path = "/v1/phoneVerfyCode")
	public void verfyCode(@RequestBody PhoneVerfyCodeEvent phoneVerfyCodeEvent) {
		log.debug("phoneVerfyCodeEvent={}", phoneVerfyCodeEvent);
		smsService.verfy(phoneVerfyCodeEvent);
	}

}
