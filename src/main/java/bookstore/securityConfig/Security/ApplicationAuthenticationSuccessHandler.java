package bookstore.securityConfig.Security;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
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
