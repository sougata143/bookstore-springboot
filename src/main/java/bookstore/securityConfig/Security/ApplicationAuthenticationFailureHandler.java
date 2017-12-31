package bookstore.securityConfig.Security;

import com.sougata.bookstore.securityConfig.Security.SecurityExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class ApplicationAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Autowired
    SecurityExceptionHandler securityExceptionHandler;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response, AuthenticationException exception)
            throws IOException, ServletException {
        securityExceptionHandler.handleException(request,response,exception);
    }
}
