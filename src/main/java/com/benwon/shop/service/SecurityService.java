package com.benwon.shop.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.oauth2.common.OAuth2AccessToken;
//import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
//import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class SecurityService {

//    private final ResourceServerTokenServices resourceServerTokenServices;
//
//    public String getUID() {
//        log.debug("getUID");
//        
//        log.debug("SecurityContextHolder.getContext={}", SecurityContextHolder.getContext());
//        log.debug("getAuthentication={}", SecurityContextHolder.getContext().getAuthentication());
//        log.debug("getDetails={}", SecurityContextHolder.getContext().getAuthentication().getDetails());
//        log.debug("OAuth2AuthenticationDetails={}", ((OAuth2AuthenticationDetails) SecurityContextHolder.getContext().getAuthentication().getDetails()));
//        log.debug("getTokenValue={}", ((OAuth2AuthenticationDetails) SecurityContextHolder.getContext().getAuthentication().getDetails()).getTokenValue());
//        String accessToken = ((OAuth2AuthenticationDetails) SecurityContextHolder.getContext().getAuthentication().getDetails()).getTokenValue();
//        log.debug("accessToken={}", accessToken);
//        OAuth2AccessToken oAuth2AccessToken = resourceServerTokenServices.readAccessToken(accessToken);
//        Map<String, Object> additionalInformation = oAuth2AccessToken.getAdditionalInformation();
//        String uid = (String) additionalInformation.get("uid");
//        return uid;
//    }
}
