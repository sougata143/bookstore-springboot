package com.sougata.bookstore.securityConfig.Security;

import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SecurityExceptionFilter implements Filter {

    @Autowired
    SecurityExceptionHandler securityExceptionHandler;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        try{
            filterChain.doFilter(servletRequest,servletResponse);
        }catch (Exception e){
            HttpServletResponse response = null;
            HttpServletRequest request = null;
            securityExceptionHandler.handleException(request,response,e);
        }

    }

    @Override
    public void destroy() {

    }
}
