package com.sougata.bookstore.securityConfig;

import com.sougata.bookstore.securityConfig.Security.ApplicationAuthenticationFailureHandler;
import com.sougata.bookstore.securityConfig.Security.ApplicationAuthenticationFilter;
import com.sougata.bookstore.securityConfig.Security.ApplicationAuthenticationSuccessHandler;
import com.sougata.bookstore.securityConfig.Security.SecurityExceptionFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    AuthenticationEntryPoint entryPoint;
    @Autowired
    LogoutSuccessHandler logoutSuccessHandler;
    @Autowired
    UserDetailsService userDetailsService;
    @Autowired
    ApplicationAuthenticationSuccessHandler applicationAuthenticationSuccessHandler;
    @Autowired
    ApplicationAuthenticationFailureHandler applicationAuthenticationFailureHandler;
    @Autowired
    SecurityExceptionFilter securityExceptionFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers("**/api/user/**").permitAll()
                    .antMatchers("**/api/books/**").permitAll()
                .anyRequest().authenticated().and()
                .exceptionHandling().authenticationEntryPoint(entryPoint)
                .and().logout().logoutSuccessHandler(logoutSuccessHandler)
                .and().csrf().disable();

        http.addFilterBefore(getApplicationAuthenticationFilter(),UsernamePasswordAuthenticationFilter.class);

        http.addFilterAfter(securityExceptionFilter, ChannelProcessingFilter.class);

    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("**/resources/**");
    }

    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(new BCryptPasswordEncoder(11));
    }

    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    }

    @Bean
    public ApplicationAuthenticationFilter getApplicationAuthenticationFilter() throws Exception {
        ApplicationAuthenticationFilter applicationAuthenticationFilter = null;
        applicationAuthenticationFilter.setAuthenticationManager(authenticationManager());
        applicationAuthenticationFilter.setAuthenticationSuccessHandler(applicationAuthenticationSuccessHandler);
        applicationAuthenticationFilter.setAuthenticationFailureHandler(applicationAuthenticationFailureHandler);

        return applicationAuthenticationFilter;
    }

    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }
}
