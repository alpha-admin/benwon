package com.benwon.shop.controller.rest;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.benwon.shop.bo.UserProfileBo;
import com.benwon.shop.service.TokenService;
import com.benwon.shop.service.UserProfileService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api")
public class UserProfileRestController {
	
	private final UserProfileService userProfileService;
	private final TokenService tokenService;
	
	@PutMapping(path = "/v1/userProfile")
	public void update(
			@RequestHeader("Authorization") String authorization, 
			@RequestBody UserProfileBo userProfileBo) {
		log.debug("authorization={}, userProfileBo={}", authorization, userProfileBo);
		String uid = tokenService.verify(authorization);
		userProfileService.update(uid, userProfileBo);

	}

}
