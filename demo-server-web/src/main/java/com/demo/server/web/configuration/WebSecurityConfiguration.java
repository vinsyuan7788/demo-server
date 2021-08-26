package com.demo.server.web.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author Vince Yuan
 * @date 08/26/2021
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(WebSecurity web) {
        // 忽略（即放行）所有请求
        web.ignoring().anyRequest();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 认证请求时，对于所有请求，统统允许
        http.authorizeRequests().anyRequest().permitAll();
    }
}
