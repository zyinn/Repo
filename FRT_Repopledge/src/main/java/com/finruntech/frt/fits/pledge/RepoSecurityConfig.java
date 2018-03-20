package com.finruntech.frt.fits.pledge;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * spring security interest
 * Created by yinan.zhang on 2018/1/10.
 */
@Configuration
@EnableWebSecurity
public class RepoSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 设置能够访问的接口信息
     * @param http HttpSecurity
     * @throws Exception e
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .authorizeRequests()
                .antMatchers("pledge/*","/pledge/*","/pledge/*/*","/api/v1/pledge/*","/api/v1/send/*","mqmsg/pledge/*","stoprepopledge","/api/v1/*")
                .permitAll()
                .anyRequest()
                .authenticated();
    }

}
