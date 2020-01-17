package com.benwon.shop.service;

import com.benwon.shop.configuration.properties.AuthProperty;
import com.benwon.shop.configuration.properties.BenwonProperties;
import com.benwon.shop.dto.AuthTokenDto;
import com.benwon.shop.dto.OAuthTokenRsDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {
    private final ObjectMapper objectMapper;
    private final BenwonProperties benwonProperties;

    public OAuthTokenRsDto getToken(String code, String state) throws IOException {
        AuthProperty auth = benwonProperties.getAuth();
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
        map.add("grant_type", "authorization_code");
        map.add("code", code);
        map.add("redirect_uri", auth.getRedirectUri());
        map.add("client_id", auth.getClientId());
        map.add("client_secret", auth.getClientSecret());
        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<MultiValueMap<String, String>>(map, headers);
        ResponseEntity<String> response = restTemplate.exchange(auth.getToken(), HttpMethod.POST, entity, String.class);
        log.debug("getToken={}", response.getBody());
        OAuthTokenRsDto oAuthTokenRsDto = objectMapper.readValue(response.getBody(), OAuthTokenRsDto.class);
        return oAuthTokenRsDto;
    }
}
