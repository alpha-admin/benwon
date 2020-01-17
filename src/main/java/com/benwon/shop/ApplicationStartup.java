package com.benwon.shop;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.apache.commons.codec.binary.Base64;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.benwon.shop.dto.OAuthTokenRsDto;
import com.benwon.shop.dto.line.LineIdToken;
import com.benwon.shop.entity.UserAccounts;
import com.benwon.shop.entity.UserProfile;
import com.benwon.shop.repository.SmsRecordRepository;
import com.benwon.shop.service.SmsService;
import com.benwon.shop.service.TokenService;
import com.benwon.shop.service.UserProfileService;
import com.benwon.shop.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ApplicationStartup {

	private final SmsService smsService;
	private final SmsRecordRepository smsRecordRepository;

	private final TokenService tokenService;
	private final UserService userService;
	private final UserProfileService userProfileService;
	private final ObjectMapper objectMapper;

	static final long EXPIRATIONTIME = 432_000_000; // 5天
	static final String TOKEN_PREFIX = "Bearer"; // Token前缀
	static final String HEADER_STRING = "Authorization";// 存放Token的Header Key
	private final String TOKEN_SECRET = "w^TA$fgw";

	@EventListener(ApplicationReadyEvent.class)
	public void afterStartup() {

//		UserAccounts userAccounts = userService.findById("523547338094936064").get();
//		OAuthTokenRsDto oAuthTokenRsDto = tokenService.generateByUser(userAccounts);
//		System.out.println(oAuthTokenRsDto);
		
		
		
//		Optional<UserAccounts> optionalAccounts = userService.findById(userAccounts.getId());
//		Optional<UserProfile> optionalUserProfile = userProfileService.findById(userAccounts.getId());
		
//		String[] scope = {};
//		Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
//		String token = JWT.create()
//				.withClaim("uid", "521714981474013184")
//				.withAudience("benwon", "account") // aud 資源ID
//				.withClaim("user_name", "521714981474013184")
//				.withArrayClaim("scope", scope)
//				.withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
//				.withArrayClaim("authorities", "group_buyer".split(","))
//				.withJWTId(UUID.randomUUID().toString())
//				.withClaim("client_id", "benwon_web")
//				.sign(algorithm);
////        .withIssuer("auth0")// iss
////		.withSubject("asd")// sub 
//
//		System.out.println(token);

//		tokenService.verify(token);

		// smsService.send("0970996200");
//		LocalDateTime before30min = LocalDateTime.now().minusMinutes(30);
//		Long count = smsRecordRepository.countByCreatedDateAfter(before30min);
//		System.out.println("累計=" + count);
//		
//		Optional<SmsRecord> smsRecordOptional= smsRecordRepository.findTopByPhoneNumberOrderByCreatedDateDesc("0970996200");
//		
//		if(smsRecordOptional.isPresent()) {
//			System.out.println(smsRecordOptional.get().getId());
//			long seconds = ChronoUnit.SECONDS.between(smsRecordOptional.get().getCreatedDate(), LocalDateTime.now());
//			System.out.println("差"+seconds+"秒");
//		}
//		
//
//		
//		System.out.println(RandomStringUtils.randomAlphanumeric(10));
////
//		RestTemplate restTemplate = new RestTemplate();
//
//		HttpHeaders headers = new HttpHeaders();
//		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
//
//		MultiValueMap<String, String> postParameters = new LinkedMultiValueMap<>();
//		postParameters.add("UID", "0953533139");// 帳號
//		postParameters.add("PWD", "bweg");
//		postParameters.add("SB", "電話認證碼");
//		postParameters.add("MSG", "您的認證碼是 %s，本圓謝謝您的支持，請依步驟完成註冊。");
//		postParameters.add("DEST", "0970996200");
//		postParameters.add("ST", "");
//
//		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(postParameters, headers);

//		ResponseEntity<String> response = restTemplate.postForEntity(sendSMSUrl, request, String.class);

//		System.out.println(response.getBody());
		// 87.00,1,1,0,b429109a-040c-42ff-9b58-7c2a365eece9

	}

}
