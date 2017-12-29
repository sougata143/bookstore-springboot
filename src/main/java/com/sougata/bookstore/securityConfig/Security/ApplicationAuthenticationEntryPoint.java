package com.sougata.bookstore.securityConfig.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ApplicationAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Autowired
    SecurityExceptionHandler securityExceptionHandler;

    AuthenticationException authenticationException;

    @Override
    public void commence(HttpServletRequest httpServletRequest,
                         HttpServletResponse httpServletResponse, AuthenticationException e)
            throws IOException, ServletException {
            securityExceptionHandler.handleException(httpServletRequest,httpServletResponse,authenticationException);
    }
}
