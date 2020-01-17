package com.benwon.shop.controller.rest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.benwon.shop.bo.InviteCodeGenerateEvent;
import com.benwon.shop.bo.InviteCodeUseEvent;
import com.benwon.shop.exception.BenwonConflictException;
import com.benwon.shop.service.InviteCodeService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api")
public class InviteCodeRestController {

	private final InviteCodeService inviteCodeService;

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(path = "/v1/inviteCodes")
	public void create(@RequestBody InviteCodeGenerateEvent inviteCodeGenerateEvent) {
		inviteCodeService.generate(inviteCodeGenerateEvent);
	}

	@PostMapping(path = "/v1/inviteCodes/use")
	public void use(@RequestHeader("Authorization") String authorization,
			@RequestBody InviteCodeUseEvent inviteCodeUseEvent) {
		Boolean useRs = inviteCodeService.use(authorization, inviteCodeUseEvent);
		if (useRs.equals(Boolean.FALSE)) {
			throw new BenwonConflictException("邀請碼已被使用");
		}
	}

}
