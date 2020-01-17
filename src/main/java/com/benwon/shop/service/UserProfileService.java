package com.benwon.shop.service;

import com.benwon.shop.bo.UserProfileBo;
import com.benwon.shop.dto.LineProfileDto;
import com.benwon.shop.entity.AuthRecords;
import com.benwon.shop.entity.UserProfile;
import com.benwon.shop.repository.AuthRecordsRepository;
import com.benwon.shop.repository.UserProfileRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserProfileService {
	private final ObjectMapper objectMapper;
	private final UserProfileRepository userProfileRepository;
	private final AuthRecordsRepository authRecordsRepository;

	public UserProfile createFromLine(String userId, String lineAccessToken) {
		LineProfileDto lineProfileDto = this.getLineUserProfiles(lineAccessToken);
		log.debug("lineProfileDto={}", lineProfileDto);
		UserProfile userProfile = new UserProfile();
		userProfile.setId(userId);
//		userProfile.setLineId(lineProfileDto.getUserId());
		userProfile.setLineUserId(lineProfileDto.getUserId());
		userProfile.setRealName(lineProfileDto.getDisplayName());
		userProfile.setNickName(lineProfileDto.getDisplayName());
		userProfile.setPicture(lineProfileDto.getPictureUrl());
		userProfile.setLineUserId(lineProfileDto.getUserId());
		userProfile.setFinished(0);
		userProfile.setCreatedBy("");
		userProfile.setCreatedDate(LocalDateTime.now());
		userProfile.setLastModifiedBy("");
		userProfile.setLastModifiedDate(LocalDateTime.now());
		userProfile = userProfileRepository.save(userProfile);
		return userProfile;
	}

	public Optional<UserProfile> findById(String id) {
		Optional<UserProfile> userProfileOptional = userProfileRepository.findById(id);
		return userProfileOptional;
	}

	private LineProfileDto getLineUserProfiles(String accessToken) {
		LineProfileDto lineProfileDto = null;
		try {
			// @formatter:off
			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
			headers.set("Authorization", "Bearer " + accessToken);
			// headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
			HttpEntity entity = new HttpEntity(null, headers);
			ResponseEntity<String> response = restTemplate.exchange("https://api.line.me/v2/profile", HttpMethod.GET, entity, String.class);
			log.debug("gettingUserProfiles={}", response.getBody());
			lineProfileDto = objectMapper.readValue(response.getBody(), LineProfileDto.class);
			// @formatter:on
		} catch (IOException e) {
			e.printStackTrace();
		}
		return lineProfileDto;
		// {"userId":"U66491639ac9249ff00a2135b2ed3c10e","displayName":"朱尚禮","pictureUrl":"https://profile.line-scdn.net/0m03f2330b7251770424e1369f58f0d3a4868033aee9e2"}
	}

	public void update(String uid, UserProfileBo userProfileBo) {
		log.debug("update uid={}, userProfileBo={}", uid, userProfileBo);
		UserProfile userProfile = userProfileRepository.getOne(uid);
		userProfile.setRealName(userProfileBo.getRealName());
		userProfile.setLineId(userProfileBo.getLineId());
		userProfile.setLineUserName(userProfileBo.getLineUserName());
		userProfile.setMobilePhone(userProfileBo.getMobilePhone());
		userProfile.setEmail(userProfileBo.getEmail());
		userProfile.setCounty(userProfileBo.getCounty());
		userProfile.setDistrict(userProfileBo.getDistrict());
		userProfile.setZipcode(userProfileBo.getZipcode());
		userProfile.setAddress(userProfileBo.getAddress());
		userProfile.setFinished(1);
		userProfile.setLastModifiedBy("");
		userProfile.setLastModifiedDate(LocalDateTime.now());
		userProfileRepository.save(userProfile);
	}
}
