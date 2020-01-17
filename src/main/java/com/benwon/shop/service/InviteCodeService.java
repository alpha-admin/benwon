package com.benwon.shop.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import com.benwon.shop.bo.InviteCodeGenerateEvent;
import com.benwon.shop.bo.InviteCodeUseEvent;
import com.benwon.shop.entity.InviteCode;
import com.benwon.shop.entity.UserAccounts;
import com.benwon.shop.repository.InviteCodeRepository;
import com.benwon.shop.repository.UserAccountsRepository;

import lombok.RequiredArgsConstructor;
import xyz.downgoon.snowflake.Snowflake;

@Service
@RequiredArgsConstructor
public class InviteCodeService {
	private final Snowflake snowflake;
	private final InviteCodeRepository inviteCodeRepository;
	private String codeBaseValue = "ABCDEFGHJKLMNPQRSTUVWXYZ23456789";
	private char[] codeBase = codeBaseValue.toCharArray();
	private final TokenService tokenService;
	private final UserAccountsRepository userAccountsRepository;

	public Boolean use(String authorization, InviteCodeUseEvent inviteCodeUseEvent) {
		Boolean useRs = false;
		Optional<InviteCode> inviteCodeOptional = inviteCodeRepository
				.findByInviteCode(inviteCodeUseEvent.getInviteCode());
		if (inviteCodeOptional.isPresent()) {
			InviteCode inviteCode = inviteCodeOptional.get();
			if (inviteCode.getCodeStatus().equals(0)) {
				useRs = true;
				inviteCode.setCodeStatus(1);
				inviteCode.setLastModifiedDate(LocalDateTime.now());
				inviteCodeRepository.save(inviteCode);
				String uid = tokenService.verify(authorization);
				UserAccounts userAccounts = userAccountsRepository.getOne(uid);
				userAccounts.setIsEnabled(Boolean.TRUE);
				userAccountsRepository.save(userAccounts);
			}
		}
		return useRs;
	}

	public void use(String inviteCodeStr) {
		InviteCode inviteCode = inviteCodeRepository.findByInviteCode(inviteCodeStr).get();
		if (inviteCode.getCodeStatus().equals(1))
			throw new RuntimeException("the invitation code your just input is invalid");
		inviteCode.setCodeStatus(1);
		inviteCode.setLastModifiedDate(LocalDateTime.now());
		inviteCodeRepository.save(inviteCode);
	}

	public void generate(InviteCodeGenerateEvent inviteCodeGenerateEvent) {
		for (int i = 0; i < inviteCodeGenerateEvent.getInviteCodeCount(); i++) {
			String id = String.valueOf(snowflake.nextId());
			String inviteCodeStr = RandomStringUtils.random(5, codeBase);
			InviteCode inviteCode = new InviteCode();
			inviteCode.setId(id);
			inviteCode.setInviteCode(inviteCodeStr);
			inviteCode.setCodeStatus(0);
			inviteCode.setCreatedBy("");
			inviteCode.setCreatedDate(LocalDateTime.now());
			inviteCode.setLastModifiedBy("");
			inviteCode.setLastModifiedDate(LocalDateTime.now());
			inviteCodeRepository.save(inviteCode);
		}

	}

}
