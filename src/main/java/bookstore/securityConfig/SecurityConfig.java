package bookstore.securityConfig;

import com.sougata.bookstore.securityConfig.Security.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private ApplicationAuthenticationEntryPoint authenticationEntryPoint;
    @Autowired
    private LogoutSuccessHandler logoutSuccessHandler;
    @Autowired
    private ApplicationAuthenticationSuccessHandler authenticationSuccessHandler;
    @Autowired
    private ApplicationAuthenticationFailureHandler authenticationFailureHandler;
    @Autowired
    private SecurityExceptionFilter exceptionFilter;

    /**
     * Build a Spring authentication manager and configure it to use the Application's
     * UserDetailsService to handle username - password authentication. For encryption
     * configure the builder to use BCrypt Password Encoding technique provided by
     * Spring via {@link BCryptPasswordEncoder}.
     *
     * @param authenticationManagerBuilder Spring's authentication manager injected
     * @throws Exception
     */

    @Autowired
    public void configureAthentiction(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception{
        authenticationManagerBuilder.userDetailsService(userDetailsService)
                .passwordEncoder(new BCryptPasswordEncoder(11));
    }

    /**
     * Create a new authentication manager bean that will take care of the authentication
     * needs. The authentication manager is already configured using the {@link AuthenticationManagerBuilder}
     */
    public AuthenticationManager authenticationManagerBean() throws Exception{
        return super.authenticationManagerBean();
    }

    /**
     * Create a bean of the custom filter {@link JwtAuthenticationFilter} that will registered
     * by the servlet container to intercept the requests.
     *
     * @return {@link JwtAuthenticationFilter} The filter object
     * @throws Exception
     */

    @Bean
    public JwtAuthenticationFilter authenticationTokenFilterBean() throws Exception{
        JwtAuthenticationFilter authenticationTokenFilter = new JwtAuthenticationFilter();
        authenticationTokenFilter.setAuthenticationManager(super.authenticationManagerBean());
        return authenticationTokenFilter;
    }

    /**
     * Override the default configuration used by Spring Security. Configure Spring Security
     * to intercept all the URL's and pipe it through the {@link AuthenticationEntryPoint}
     * that has been configured in the application. Also this method overrides the default route
     * permissions and registers new route permission. Configure {@link HttpSecurity} to use
     * our own {@link JwtAuthenticationFilter}
     *
     * @param http
     */

    @Override
    protected void configure(HttpSecurity http) throws Exception {
            http
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .and()
                    .authorizeRequests()
                    .antMatchers("/api/user/**").hasRole("ROLE ADMIN")
                    .antMatchers("/api/books/").permitAll()
                    .antMatchers("/api/books/save").hasRole("ROLE ADMIN")
                    .antMatchers("/api/books/delete/").hasRole("ROLE ADMIN")
                    .antMatchers("/api/books/save").hasRole("ROLE MANAGER")
                    .anyRequest().authenticated().and()
                    .exceptionHandling().authenticationEntryPoint(authenticationEntryPoint)
                    .and().logout().logoutSuccessHandler(logoutSuccessHandler)
                    .and().csrf().disable()
            ;

//            Custom JWT based authetication
            http.addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);

            http.addFilterBefore(getApplicationAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
            http.addFilterAfter(exceptionFilter, ChannelProcessingFilter.class);

    }

    @Bean
    public ApplicationAuthenticationFilter getApplicationAuthenticationFilter() throws Exception {
        ApplicationAuthenticationFilter applicationAuthenticationFilter = new ApplicationAuthenticationFilter();
        applicationAuthenticationFilter.setAuthenticationManager(authenticationManager());
        applicationAuthenticationFilter.setAuthenticationSuccessHandler(authenticationSuccessHandler);
        applicationAuthenticationFilter.setAuthenticationFailureHandler(authenticationFailureHandler);

        return applicationAuthenticationFilter;
    }


}
