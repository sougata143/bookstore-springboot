package com.sougata.bookstore.securityConfig.Security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sougata.bookstore.ApiException;

import javax.naming.AuthenticationException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SecurityExceptionHandler {

    ApiException apiException;
    AuthenticationException authenticationException;

    public void handleException(HttpServletRequest request, HttpServletResponse response, Exception e) throws IOException {
        response.setContentType("application/json");
        if(e == apiException){
            ApiException apiE = (ApiException) e;
            response.setStatus(apiE.getStatus().value());
        }else if (e == authenticationException){
            response.setStatus(response.SC_UNAUTHORIZED);
        }else {
            response.setStatus(response.SC_INTERNAL_SERVER_ERROR);
        }

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(response.getOutputStream(), e.getMessage());

    }

}
