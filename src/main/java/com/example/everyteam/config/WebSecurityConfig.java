package com.example.everyteam.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().cors();
        http.httpBasic().disable()
                .formLogin().disable()  //formlogin이나 기본 httplogin방식을 아예 쓰지 않는다.
                .authorizeRequests()    //요청에 대한 사용권한 체크
                .antMatchers("/**").permitAll();
    }
}
