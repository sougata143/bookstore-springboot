package bookstore.securityConfig.Security;

import com.sougata.bookstore.securityConfig.Security.SecurityExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
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
//            HttpServletRequest req = new HttpServletRequest();
//            HttpServletResponse res = new HttpServletResponse();
            securityExceptionHandler.handleException((HttpServletRequest) servletRequest, (HttpServletResponse) servletResponse, e);
        }

    }

    @Override
    public void destroy() {

    }
}
