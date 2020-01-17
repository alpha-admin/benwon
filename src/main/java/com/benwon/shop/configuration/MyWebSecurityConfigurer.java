package com.benwon.shop.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
//@EnableWebSecurity
@RequiredArgsConstructor
public class MyWebSecurityConfigurer { // extends WebSecurityConfigurerAdapter 
	
    /**
     * 顾名思义，WebSecurity主要是配置跟web资源相关的，比如css、js、images等等，但是这个还不是本质的区别，关键的区别如下：
     * <p>
     * ingore是完全绕过了spring security的所有filter，相当于不走spring security
     * permitall没有绕过spring security，其中包含了登录的以及匿名的。
     * https://segmentfault.com/a/1190000012160850
     *
     * @param web
     * @throws Exception
     */
//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        web.ignoring().antMatchers("/favicon.ico");
//        web.ignoring().antMatchers("/css/**");
//        web.ignoring().antMatchers("/js/**");
//        web.ignoring().antMatchers("/fonts/**");
//        web.ignoring().antMatchers("/plugins/**");
//        web.ignoring().antMatchers("/dist/**");
//        web.ignoring().antMatchers("/authorize");
//        web.ignoring().antMatchers("/lineLogin");
//        web.ignoring().antMatchers("/lineLoginCallback");
//        web.ignoring().antMatchers("/registerFromLine");
////        web.httpFirewall(allowUrlEncodedSlashHttpFirewall());
//       web.ignoring().antMatchers("/api/**");
//
//
//    }
}
