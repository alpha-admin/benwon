package com.benwon.shop.controller.page;

import java.util.Optional;

import org.apache.commons.codec.net.URLCodec;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.benwon.shop.configuration.properties.LineProperties;
import com.benwon.shop.dto.OAuthTokenRsDto;
import com.benwon.shop.dto.line.LineAuthDto;
import com.benwon.shop.dto.line.LineIdToken;
import com.benwon.shop.entity.UserAccounts;
import com.benwon.shop.entity.UserLine;
import com.benwon.shop.entity.UserProfile;
import com.benwon.shop.repository.UserLineRepository;
import com.benwon.shop.service.LineAuthService;
import com.benwon.shop.service.TokenService;
import com.benwon.shop.service.UserProfileService;
import com.benwon.shop.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginPageController {
	private final LineProperties lineProperties;
	private final LineAuthService lineAuthService;
	private final UserLineRepository userLineRepository;
	private final UserService userService;
	private final TokenService tokenService;
	private final UserProfileService userProfileService;

	@GetMapping(value = { "/line/login" })
	public String authorize(Model model) {
		try {
			model.addAttribute("client_id", lineProperties.getClientId());
			model.addAttribute("redirect_uri", new URLCodec().encode(lineProperties.getRedirectUri()));
			model.addAttribute("state", RandomStringUtils.randomAlphanumeric(4).toUpperCase());
			model.addAttribute("bot_prompt", lineProperties.getBotPrompt());
			model.addAttribute("scope", new URLCodec().encode(lineProperties.getScope()));
			model.addAttribute("ui_locales", lineProperties.getUiLocales());
		} catch (

		Exception e) {
			log.error("解析授權資料失敗 ", e);
		}
		return "lineLogin";
	}

	@GetMapping(value = { "/line/loginCallback" })
	public String lineLoginCallback(Model model, @RequestParam(value = "code", required = false) String code,
			@RequestParam(value = "state", required = false) String state,
			@RequestParam(value = "friendship_status_changed", required = false) Boolean friendship_status_changed) {
		String naxtPage = "";
		try {
			log.debug("code={}, state={}, friendship_status_changed={}", code, state, friendship_status_changed);
			UserAccounts userAccounts = null;
			UserProfile userProfile = null;
			// 取得 Line token
			LineAuthDto lineAuthDto = lineAuthService.getToken(code, state);
			// 取得 ID
			LineIdToken lineIdToken = lineAuthService.getLineIdToken(lineAuthDto);
			// 是否有註冊過
			UserLine userLine = userLineRepository.findByLineId(lineIdToken.getSub());
			log.debug("userLine={}", userLine);
			if (userLine == null) {// 使用 Line ID 建立帳號 & 資料
				userAccounts = userService.createLineUser(lineIdToken.getSub());
				userProfileService.createFromLine(userAccounts.getId(), lineAuthDto.getAccessToken());
			}else {
				userAccounts = userService.findById(userLine.getUserId()).get();
			}
			// 使用 ID 找出主帳號
			Optional<UserAccounts> optionalAccounts = userService.findById(userAccounts.getId());
			Optional<UserProfile> optionalUserProfile = userProfileService.findById(userAccounts.getId());
			if (optionalAccounts.isPresent()) {
				userAccounts = optionalAccounts.get();
				// 產生 Token
				OAuthTokenRsDto oAuthTokenRsDto = tokenService.generateByUser(userAccounts);
				model.addAttribute("accessToken", oAuthTokenRsDto.getAccessToken());
				model.addAttribute("refreshToken", oAuthTokenRsDto.getRefreshToken());
				if (userAccounts.getIsEnabled().equals(Boolean.FALSE)) {// 尚未啟用, 到邀請碼啟動頁面
					naxtPage = "sign_up_invite_code";
				} else {// 如果已啟用, 檢查資料是否完整
					userProfile = optionalUserProfile.get();
					if(userProfile.getFinished().equals(Boolean.FALSE)) {// 已啟用但電話跟資料尚未補齊
						naxtPage = "sign_up_user_profile_with_token";
					}else {
						naxtPage = "lineLoginSuccess";
					}
				}
			}
		} catch (Exception e) {
			log.error("解析授權資料失敗 ", e);
		}
		return naxtPage;
	}
}
