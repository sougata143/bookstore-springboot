package com.sougata.bookstore.securityConfig.Security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sougata.bookstore.ApiException;
import com.sougata.bookstore.domain.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.xml.sax.SAXException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.validation.Validator;
import java.io.IOException;
import java.net.BindException;

public class ApplicationAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
    protected ApplicationAuthenticationFilter(String defaultFilterProcessesUrl) {
        super(defaultFilterProcessesUrl);
    }
    
    @Autowired
    Validator validator;
    
    private String loginContentType ="application/json;charset=UTF-8";
    private String loginHttpMethod = "POST";
    private String login = "/login";
    private String LOGIN_REQUEST = "Login Request";
    
    @Override
    public Authentication attemptAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws AuthenticationException, IOException, ServletException {
        
        if(loginHttpMethod != httpServletRequest.getMethod()){
            throw new AuthenticationServiceException("Authentication Method Not Supprted"+httpServletRequest.getMethod());
        }
        
        String contentType = httpServletRequest.getContentType();
        if(contentType != null){
            contentType = contentType.replace(("\\s+").toString(), "");
        }
        if (loginContentType != contentType){
            throw new AuthenticationServiceException("Content Type"+httpServletRequest.getContentType()+"Not Supported");
        }

        try {
            ValidateLoginRequest(getLoginRequest(httpServletRequest));
        } catch (SAXException e) {
            e.printStackTrace();
        }

        LoginRequest loginRequest = null;
        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(loginRequest.username
                ,loginRequest.password);
        authRequest.setDetails(authenticationDetailsSource.buildDetails(httpServletRequest));
        
        return this.getAuthenticationManager().authenticate(authRequest);
    }

    private void ValidateLoginRequest(LoginRequest loginRequest) throws IOException, SAXException {
        BindException bindException = new BindException();
        validator.validate((Source) loginRequest, (Result) bindException);
        if (bindException != null){
            throw new ApiException(bindException.fillInStackTrace(), HttpStatus.BAD_REQUEST);
        }
    }

    private LoginRequest getLoginRequest(HttpServletRequest httpServletRequest) {
        LoginRequest loginRequest = null;
        ObjectMapper objectMapper = new ObjectMapper();

        try{
            loginRequest = objectMapper.readValue(httpServletRequest.getReader(), LoginRequest.class);
        }catch (Exception e){
            throw new ApiException("Invalid JSON format of Credentials", HttpStatus.BAD_REQUEST);
        }

        return loginRequest;
    }
}
