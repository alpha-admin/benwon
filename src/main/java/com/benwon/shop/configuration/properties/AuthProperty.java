package com.benwon.shop.configuration.properties;

import lombok.Data;

@Data
public class AuthProperty {
    private String url;
    private String token;
    private String clientId;
    private String clientSecret;
    private String redirectUri;
}
