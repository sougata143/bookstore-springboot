package com.sougata.bookstore.securityConfig.utils;

public class JwtTokenResponseDTO {
    private String token;

    public JwtTokenResponseDTO() {
        super();
    }

    public JwtTokenResponseDTO(String token) {
        this.setToken(token);
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
