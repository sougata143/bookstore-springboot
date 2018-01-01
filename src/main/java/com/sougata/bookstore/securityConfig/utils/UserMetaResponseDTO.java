package com.sougata.bookstore.securityConfig.utils;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserMetaResponseDTO {
    @JsonProperty("userid")
    private Long userid;

    @JsonProperty("username")
    private String username;

    @JsonProperty("email")
    private String email;

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
