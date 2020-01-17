package com.benwon.shop.service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.benwon.shop.bo.PhoneVerfyCodeEvent;
import com.benwon.shop.bo.PhoneVerfyCodeSendEvent;
import com.benwon.shop.configuration.properties.BenwonProperties;
import com.benwon.shop.configuration.properties.SmsProperty;
import com.benwon.shop.entity.SmsRecord;
import com.benwon.shop.exception.BenwonConflictException;
import com.benwon.shop.repository.SmsRecordRepository;

import lombok.RequiredArgsConstructor;
import xyz.downgoon.snowflake.Snowflake;

@Service
@RequiredArgsConstructor
public class SmsService {
	private final Snowflake snowflake;
	private final BenwonProperties benwonProperties;
	private final SmsRecordRepository smsRecordRepository;
	
	/**
	 * 發送驗證碼
	 * @param phoneNumber
	 * @return
	 */
	public SmsRecord send(PhoneVerfyCodeSendEvent phoneVerfyCodeSendEvent) {
		SmsProperty smsProperty = benwonProperties.getSms();
		String phoneNumber = phoneVerfyCodeSendEvent.getMobilePhone();
		Optional<SmsRecord> smsRecordOptional= smsRecordRepository.findTopByPhoneNumberOrderByCreatedDateDesc(phoneNumber);
		if(smsRecordOptional.isPresent()) {
			long seconds = ChronoUnit.SECONDS.between(smsRecordOptional.get().getCreatedDate(), LocalDateTime.now());
			if(seconds < smsProperty.getRetryWaitSecond()) {
				throw new BenwonConflictException("請稍候30 秒再嘗試發送");
			}
		}
		LocalDateTime beforeMinut = LocalDateTime.now().minusMinutes(smsProperty.getRetryIntervalMinute());
		Long count = smsRecordRepository.countByCreatedDateAfter(beforeMinut);
		if(count > smsProperty.getRetryIntervalLimit()) {
			throw new BenwonConflictException("超過重發次數, 請稍後再試, 請等待30分鐘再嘗試發送");
		}
		String id = String.valueOf(snowflake.nextId());
		String verificationCode = RandomStringUtils.random(6, false, true);		
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		MultiValueMap<String, String> postParameters = new LinkedMultiValueMap<>();
		postParameters.add("UID", smsProperty.getUid());// 帳號
		postParameters.add("PWD", smsProperty.getPwd());
		postParameters.add("SB", smsProperty.getSb());
		postParameters.add("MSG", String.format(smsProperty.getMsg(), verificationCode));
		postParameters.add("DEST", phoneNumber);
		postParameters.add("ST", "");
		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(postParameters, headers);
		ResponseEntity<String> response = restTemplate.postForEntity(smsProperty.getUrl(), request, String.class);
		SmsRecord smsRecord = new SmsRecord();
		smsRecord.setId(id);
		smsRecord.setPhoneNumber(phoneNumber);
		smsRecord.setVerificationCode(verificationCode);
		smsRecord.setVerfyStatus(0);
		smsRecord.setCreatedBy("");
		smsRecord.setCreatedDate(LocalDateTime.now());
		smsRecord.setLastModifiedBy("");
		smsRecord.setLastModifiedDate(LocalDateTime.now());
		smsRecord = smsRecordRepository.save(smsRecord);
		return smsRecord;
	}
	
	public void verfy(PhoneVerfyCodeEvent phoneVerfyCodeEvent) {
		Optional<SmsRecord> optionalSmsRecord = smsRecordRepository.findById(phoneVerfyCodeEvent.getSmsId());
		optionalSmsRecord.orElseThrow(() -> new BenwonConflictException("驗證碼錯誤"));
		SmsRecord smsRecord = optionalSmsRecord.get();
		if(smsRecord.getVerificationCode().equals(phoneVerfyCodeEvent.getVerifyCode())) {
			smsRecord.setVerfyStatus(1);
			smsRecordRepository.save(smsRecord);
		}else {
			throw new BenwonConflictException("驗證碼錯誤");
		}
	}
}
