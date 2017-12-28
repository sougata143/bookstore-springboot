package com.sougata.bookstore.securityConfig;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("sougata").password("1234").roles("admin");

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.httpBasic()
                .and()
                    .authorizeRequests()
                        .antMatchers("/api/users/**").hasRole("admin")
                        .antMatchers("/api/books/**").hasRole("admmin")
                        .antMatchers("/api/books/").permitAll()
                        .antMatchers("/api/books/save").hasRole("manager")
                .and()
                .csrf().disable().headers().frameOptions().disable();

    }
}
