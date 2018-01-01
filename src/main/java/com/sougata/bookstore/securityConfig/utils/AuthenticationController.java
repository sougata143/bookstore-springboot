package com.sougata.bookstore.securityConfig.utils;


import com.sougata.bookstore.domain.Constants;
import com.sougata.bookstore.domain.User;
import com.sougata.bookstore.securityConfig.Security.ApplicationUser;
import com.sougata.bookstore.securityConfig.Security.JwtToken;
import com.sougata.bookstore.service.UserService;
import com.sougata.bookstore.securityConfig.Security.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Date;

@RestController
@RequestMapping(value = Endpoints.AUTHENTICATION_ROUTE )
public class AuthenticationController {

    /**
     * Spring's {@link AuthenticationManager} bean autowired
     */
    @Autowired
    private AuthenticationManager authenticationManager;

    /**
     * {@link EntityMapper} bean autowired
     */
    @Autowired
    private EntityMapper entityMapper;

    /**
     * {@link JwtToken} bean autowired
     */
    @Autowired
    private JwtToken jwtToken;

    /**
     * Spring's {@link UserDetailsService} implementation {@link MyUserDetailsService} injected over here
     */
    @Autowired
    private UserDetailsService userDetailsService;

    /**
     * {@link UserService} bean autowired.
     */
    @Autowired
    private UserService userService;

    /**
     * POST request to this route along with the username and password authenticates
     * the user and returns an authentication token in the form of JWT(JSON Web Token)
     * that can be sent with the header using the key X-Auth-Token. This a one time login
     *
     * @param authenticationRequestDTO The DTO object initialized from the JSON payload
     * @param bindingResult
     * @return
     * @throws AuthenticationException
     */
    @RequestMapping(method = RequestMethod.POST, consumes= MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JwtTokenResponseDTO> authenticationRequest(@RequestBody @Valid UserAuthenticationRequestDTO authenticationRequestDTO, BindingResult bindingResult) {

        // Check if the dto validations encountered any errors
        if (bindingResult.hasErrors()) {
            throw new DtoValidationException(Messages.AUTHENTICATION_FAILURE.getMessageText(),bindingResult);
        }

        // Authenticate the user using Spring's Authentication Manager
        Authentication authentication = this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticationRequestDTO.getEmail(), authenticationRequestDTO.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetails userDetails = this.userDetailsService.loadUserByUsername(authenticationRequestDTO.getEmail());

        // Generate the token by passing in the UserDetails object
        String token = this.jwtToken.generateToken(userDetails);

        // Return the token
        return ResponseEntity.ok(new JwtTokenResponseDTO(token));
    }

    /**
     * Get the current user information by passing in the auth token issued during
     * the authentication request
     *
     * @param request The raw HttpServletRequest object
     * @return
     */
    @RequestMapping(value = Endpoints.AUTHENTICATED_USER_ROUTE, method = RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<AuthenticatedUserResponseDTO> getAuthenticatedUser(HttpServletRequest request) {

        // Get the current authentication context
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Get the current logged in user from the authentication context
        ApplicationUser applicationUser = (ApplicationUser) authentication.getPrincipal();

        // Fetch the user by its email
        User user = userService.getByEmail(applicationUser.getUsername());

        // Convert the PhewUser entity to DTO using the modelmapper
        UserMetaResponseDTO userResponseDTO = (UserMetaResponseDTO) entityMapper.entityToDto(user, UserMetaResponseDTO.class);

        // Populate a DTO that will be sent as JSON response for the current user
        AuthenticatedUserResponseDTO authenticatedUserResponseDTO = new AuthenticatedUserResponseDTO(
                new Date(),
                request.getHeader(Constants.AUTH_HEADER),
                userResponseDTO,
                user.getAuthorities());

        // Return a 200 OK with the DTO
        return ResponseEntity.ok(authenticatedUserResponseDTO);
    }

    /**
     * GET request to this route triggers an authentication token refresh
     *
     * @param request The raw HttpServletRequest object
     * @return
     */
    @RequestMapping(value = Endpoints.TOKEN_REFRESH_ROUTE, method = RequestMethod.GET)
    public ResponseEntity<JwtTokenResponseDTO> authenticationRequest(HttpServletRequest request) {

        // Fetch the authentication token sent with the headers
        String token = request.getHeader(Constants.AUTH_HEADER);

        // Get the Principal of the token from the JWT
        String username = this.jwtToken.getUsernameFromToken(token);

        // Fetch the user by the username
        ApplicationUser user = (ApplicationUser) userDetailsService.loadUserByUsername(username);

        // Check the validity of the token
        if (this.jwtToken.canTokenRefreshed(token,user.getLastPasswordReset())) {
            String refreshedToken = this.jwtToken.refreshToken(token);
            return ResponseEntity.ok(new JwtTokenResponseDTO(refreshedToken));
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }

}
