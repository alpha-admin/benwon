package com.benwon.shop.configuration;

import com.benwon.shop.configuration.properties.BenwonProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import xyz.downgoon.snowflake.Snowflake;

@RequiredArgsConstructor
@Configuration
public class ServiceConfiguration {

    private final BenwonProperties benwonProperties;

    @Bean
    Snowflake getSnowflake() {
        Snowflake snowflake = new Snowflake(benwonProperties.getDatacenterId(), benwonProperties.getWorkerId());
        return snowflake;
    }


}
