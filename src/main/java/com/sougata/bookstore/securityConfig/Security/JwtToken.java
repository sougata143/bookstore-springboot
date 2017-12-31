package com.sougata.bookstore.securityConfig.Security;

import com.sougata.bookstore.domain.Constants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Utility class for parsing, verifying and creating a JSON Web Token.
 */

 @Component
public class JwtToken {
    @Value("Goblet of Fire")
    private String jwtSecret;

    @Value("60480")
    private Long tokenExptiration;

    private String generateToken(Map<String, Object> claims){
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(this.generateExpiraationDate())
                .signWith(Constants.ALGORITHM, this.jwtSecret)
                .compact();
    }

    /**
     * Overloaded method. Generates a new token from Spring's {@link UserDetails} object.
     *
     * @param userDetails Takes a {@code UserDetails} Object
     * @return The JSON Web Token
     */

    public String generateToken(UserDetails userDetails){
        Map<String, Object> claims = new HashMap<>();
        claims.put("sub", userDetails.getUsername());
        claims.put("access", userDetails.getAuthorities());

        return this.generateToken(claims);
    }

    /**
     * Parses the token using a <em><b>Secret Key</b></em>
     *
     * @param token The token as String
     * @return {@link Claims} JWT Claims
     */

    private Claims getClaimsFromToken(String token){
        Claims claims;
        try {
            claims = Jwts.parser()
                        .setSigningKey(this.jwtSecret)
                        .parseClaimsJws(token)
                        .getBody();
        }catch (Exception e){
            claims = null;
        }
        return claims;
    }

    /**
     * Retrieve the Subject (i.e. username) from the token after parsing it
     *
     * @param token
     * @return String
     */

    public String getUsernameFromToken(String token){
        String username;
        try {
            final Claims claims = this.getClaimsFromToken(token);
            username = claims.getSubject();
        }catch (Exception e){
            username = null;
        }
        return username;
    }

    /**
     * Retrieve the creation date of the token
     *
     * @param token
     * @return Date
     */
    public Date getCreatedDateFromToken(String token) {
        Date created;
        try {
            final Claims claims = this.getClaimsFromToken(token);
            created = new Date((Long) claims.get("created"));
        } catch (Exception e) {
            created = null;
        }
        return created;
    }

    /**
     * Retrieve the expiration date of the token
     *
     * @param token The JWT Token
     * @return Date The expiration date as Java {@link Date}
     */

    public Date getExpirationDateFromToken(String token){
        Date expiration;
        try {
            final Claims claims = this.getClaimsFromToken(token);
            expiration = claims.getExpiration();
        }catch (Exception e){
            expiration = null;
        }
        return expiration;
    }

    /**
     * Returns the current timestamp
     *
     * @return {@link Date} The current timestamp
     */
    private Date generateCurrentDate() {
        return new Date(System.currentTimeMillis());
    }

    /**
     * Generate the expiration date of the token, calculated from the current timestamp
     *
     * @return <b>Date</b> Expiration date of the token
     */

    private Date generateExpiraationDate(){
        return new Date(System.currentTimeMillis()+this.tokenExptiration*1000);
    }

    /**
     * Check if the token is expired. The expiration is set during the creation of the token
     *
     * @param token The token as String
     * @return <b>true</b> if the token is expired, else it returns <b>false</b>
     */

    private Boolean isTokenExpired(String token){
        final Date expiration = this.getExpirationDateFromToken(token);
        return expiration.before(this.generateCurrentDate());
    }

    /**
     * Check to see if the token was created before the last password reset by the user.
     *
     * @param created The token creation date
     * @param lastPasswordReset
     * @return <b>true</b> if the token isn't created before last password reset, else <b>false</b>
     */
    private Boolean isCreatedBeforeLastPasswordReset(Date created, Date lastPasswordReset) {
        return lastPasswordReset != null && created.before(lastPasswordReset);
    }


    /**
     * Check to see if the token can be refreshed
     *
     * @param token
     * @param lastPasswordReset The last password reset date
     * @return <b>true</b> if the token hasn't expired and isn't created before last password reset
     */

    public Boolean canTokenRefreshed(String token, Date lastPasswordReset){
        final Date created = this.getCreatedDateFromToken(token);
        return !(this.isCreatedBeforeLastPasswordReset(created,lastPasswordReset)) && (!(this.isTokenExpired(token)));
    }

    /**
     * Refesh a token on expire
     *
     * @param token The token to refresh
     * @return The newly created token with the current timestamp
     */

    public String refreshToken(String token){
        String refreshedToken;
        try {
            final Claims claims = this.getClaimsFromToken(token);
            claims.put("Created", this.generateCurrentDate());
            refreshedToken = this.generateToken(claims);
        }catch (Exception e){
            refreshedToken = null;
        }
        return refreshedToken;
    }

    /**
     * Validate a JWT token to check its authenticity
     *
     * @param token The JWT Token
     * @param userDetails The UserDetails object that bears this token
     * @return true if valid
     */

    public Boolean validateToken(String token, UserDetails userDetails){
        ApplicationUser user = (ApplicationUser) userDetails;
        final String username = this.getUsernameFromToken(token);
        final Date created = this.getCreatedDateFromToken(token);
        return username.equals(user.getUsername())
                && !(this.isTokenExpired(token));
    }


}
