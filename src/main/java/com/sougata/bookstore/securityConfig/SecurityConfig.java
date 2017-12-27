package com.sougata.bookstore.securityConfig;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("").password("123").roles("admin");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //custom login page(no need in REST
        /*
        http.authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                    .loginPage("/bookstore/api/")
                    .loginProcessingUrl("/login")
                    .permitAll();*/

    }
}
