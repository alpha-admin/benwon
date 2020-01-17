package com.benwon.shop.service;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.benwon.shop.dto.OAuthTokenRsDto;
import com.benwon.shop.entity.RoleInfo;
import com.benwon.shop.entity.UserAccounts;
import com.benwon.shop.repository.RoleInfoRepository;
import com.benwon.shop.repository.UserProfileRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class TokenService {

	private final Integer ACCESS_EXPIRATION_TIME = 30 * 24 * 60 * 60 * 1000; // 天
	private final String TOKEN_TYPE = "bearer";
	private final Integer REFRESH_EXPIRATION_TIME = 60 * 24 * 60 * 60 * 1000; // 天
	private final String TOKEN_SECRET = "w^TA$fgw";

	private final RoleInfoRepository roleInfoRepository;
	private final UserProfileRepository userProfileRepository;

	public String verify(String authorization) {
		String uid = null;
		String[] tokenAry = authorization.split(" ");
		String tokenStr = tokenAry[1];
		log.debug("tokenStr={}", tokenStr);
		try {
			Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
			JWTVerifier verifier = JWT.require(algorithm)
//		        .withIssuer("auth0")
					.build(); // Reusable verifier instance
			DecodedJWT jwt = verifier.verify(tokenStr);
			uid = jwt.getClaim("uid").asString();
		} catch (Exception e) {
			log.error("jwt 驗證失敗 tokenStr={}", tokenStr, e);
		}
		return uid;
	}

	public OAuthTokenRsDto generateByUser(UserAccounts userAccounts) {

		List<RoleInfo> roleInfoList = roleInfoRepository.findByUserId(userAccounts.getId());

		OAuthTokenRsDto oAuthTokenRsDto = new OAuthTokenRsDto();
		oAuthTokenRsDto.setAccessToken(this.generateAccessToken(userAccounts, roleInfoList));
		oAuthTokenRsDto.setTokenType(TOKEN_TYPE);
		oAuthTokenRsDto.setRefreshToken(this.generateRefreshToken(userAccounts, roleInfoList));
		oAuthTokenRsDto.setExpiresIn(System.currentTimeMillis() + ACCESS_EXPIRATION_TIME);
		oAuthTokenRsDto.setScope(null);
		oAuthTokenRsDto.setJti(UUID.randomUUID().toString());
		return oAuthTokenRsDto;
	}

	private String generateAccessToken(UserAccounts userAccounts, List<RoleInfo> roleInfoList) {
		return this.generateJWT(userAccounts, roleInfoList, System.currentTimeMillis() + ACCESS_EXPIRATION_TIME);
	}

	private String generateRefreshToken(UserAccounts userAccounts, List<RoleInfo> roleInfoList) {
		return this.generateJWT(userAccounts, roleInfoList, System.currentTimeMillis() +REFRESH_EXPIRATION_TIME);
	}

	private String generateJWT(UserAccounts userAccounts, List<RoleInfo> roleInfoList, Long expirationTime) {
		String[] scope = {};
		String[] roleIds = roleInfoList.stream().map(RoleInfo::getId).collect(Collectors.toList()).stream()
				.toArray(String[]::new);
		Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
		String token = JWT.create()
				.withClaim("uid", userAccounts.getId())
				.withAudience("benwon") // aud 資源ID
				.withClaim("user_name", userAccounts.getId()).withArrayClaim("scope", scope)
				.withExpiresAt(new Date(System.currentTimeMillis() + expirationTime))
				.withArrayClaim("authorities", roleIds).withJWTId(UUID.randomUUID().toString())
				.withClaim("client_id", "benwon_web").sign(algorithm);
//        .withIssuer("auth0")// iss
//		.withSubject("asd")// sub 
		return token;
	}
}
