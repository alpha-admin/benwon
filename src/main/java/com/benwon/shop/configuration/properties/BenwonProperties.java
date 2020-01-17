package com.benwon.shop.configuration.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Data
@Configuration
@ConfigurationProperties(prefix = "benwon")
public class BenwonProperties {
    private Long datacenterId;
    private Long workerId;
    private Integer imagesDefaultSize;
    private List<Integer> imagesSize;
    private String defaultRoleId;
    private GcpProperty gcp;
    private AuthProperty auth;
    private SmsProperty sms;
}
