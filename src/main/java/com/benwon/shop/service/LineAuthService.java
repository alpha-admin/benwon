package com.benwon.shop.service;

import com.benwon.shop.configuration.properties.LineProperties;
import com.benwon.shop.dto.line.LineAuthDto;
import com.benwon.shop.dto.line.LineIdToken;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class LineAuthService {
	private final ObjectMapper objectMapper;
	private final LineProperties lineProperties;

	public LineAuthDto getToken(String code, String state) throws IOException {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		map.add("grant_type", "authorization_code");
		map.add("code", code);
		map.add("redirect_uri", lineProperties.getRedirectUri());
		map.add("client_id", lineProperties.getClientId());
		map.add("client_secret", lineProperties.getClientSecret());
		HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<MultiValueMap<String, String>>(map, headers);
		ResponseEntity<String> response = restTemplate.exchange("https://api.line.me/oauth2/v2.1/token",
				HttpMethod.POST, entity, String.class);
		log.debug("getToken={}", response.getBody());
		LineAuthDto lineAuthDto = objectMapper.readValue(response.getBody(), LineAuthDto.class);
		return lineAuthDto;
	}

	public LineIdToken getLineIdToken(LineAuthDto lineAuthDto) {
		List<String> tokenList = Arrays.asList(lineAuthDto.getIdToken().split("\\."));
		LineIdToken lineIdToken = null;
		try {			
			String lineIdTokenData = new String(new org.apache.commons.codec.binary.Base64().decode(tokenList.get(1).getBytes()));
//			byte[] asBytes = java.util.Base64.getMimeDecoder().decode(tokenList.get(1));
			//new String(asBytes, StandardCharsets.UTF_8)
			lineIdToken = objectMapper.readValue(lineIdTokenData, LineIdToken.class);
		} catch (Exception e) {
			log.error("getLineIdToken Error tokenList={}", tokenList, e);
		}
		return lineIdToken;
	}

	public void gettingUserProfiles(String accessToken) {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer " + accessToken);
		// headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		HttpEntity entity = new HttpEntity(null, headers);
		ResponseEntity<String> response = restTemplate.exchange("https://api.line.me/v2/profile", HttpMethod.GET,
				entity, String.class);
		log.debug("gettingUserProfiles={}", response.getBody());
		// {"userId":"U66491639ac9249ff00a2135b2ed3c10e","displayName":"朱尚禮","pictureUrl":"https://profile.line-scdn.net/0m03f2330b7251770424e1369f58f0d3a4868033aee9e2"}
	}
}
