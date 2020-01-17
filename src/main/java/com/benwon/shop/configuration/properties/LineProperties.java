package com.benwon.shop.configuration.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "line")
public class LineProperties {
    private String clientId; // CHANNEL_ID
    private String clientSecret;
    private String redirectUri;
    private String botPrompt;
    private String uiLocales;
    private String scope;
}
