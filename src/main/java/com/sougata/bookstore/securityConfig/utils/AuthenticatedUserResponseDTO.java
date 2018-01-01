package com.sougata.bookstore.securityConfig.utils;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class AuthenticatedUserResponseDTO {
    @JsonProperty("auth_timestamp")
    private Date authenticationTimestamp;

    @JsonProperty("auth_token")
    private String authToken;

    @JsonProperty("user")
    private UserMetaResponseDTO user;

    @JsonProperty("authorities")
    private String authorities;

    /**
     *
     * @param authenticationTimestamp
     * @param authToken
     * @param user
     * @param authorities
     */
    public AuthenticatedUserResponseDTO(Date authenticationTimestamp, String authToken, UserMetaResponseDTO user, String authorities) {
        this.authenticationTimestamp = authenticationTimestamp;
        this.authToken = authToken;
        this.user = user;
        this.authorities = authorities;
    }

}
