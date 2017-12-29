package com.sougata.bookstore.securityConfig.Security;

import com.sougata.bookstore.domain.ApplicationUser;
import com.sun.xml.internal.bind.v2.schemagen.xmlschema.Any;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ApplicationAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    DefaultRedirectStrategy redirectStrategy;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        ApplicationUser applicationUser = (ApplicationUser) authentication.getPrincipal();
        redirectStrategy.sendRedirect(request,response,"/api/user/");
    }

    @GetMapping("/api/user/")
    Map<String, String> getLoginInfo(HttpSession httpSession, HttpServletResponse response){
        HashMap<String, String> loginInfo = new HashMap<String, String>();

        loginInfo.put("JESSIONID",httpSession.getId());
        response.setHeader("JESSIONID", httpSession.getId());

        return loginInfo;

    }

}
