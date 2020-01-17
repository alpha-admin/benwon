package com.benwon.shop.configuration;

import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
//import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
//@EnableResourceServer
@RequiredArgsConstructor
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class OAuth2ResourceServer  {// extends ResourceServerConfigurerAdapter
	

//    private final ResourceServerProperties resourceServerProperties;
//
//    @Override
//    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
//        log.debug(">> OAuth2ResourceServer.configure ResourceServerSecurityConfigurer");
//        resources.resourceId(resourceServerProperties.getResourceId());
//    }
//
//    @Override
//    public void configure(HttpSecurity http) throws Exception {
//        log.debug(">> OAuth2ResourceServer.configure HttpSecurity");
//        http
//                .cors().disable()
//                .csrf().disable()
//                .httpBasic().disable()
//                .authorizeRequests()
//                .anyRequest().permitAll();
//    }
}