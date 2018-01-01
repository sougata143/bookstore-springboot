package com.sougata.bookstore.securityConfig.utils;

public class Endpoints {

    private Endpoints(){}

    /**
     * Authentication and account related endpoints
     */
    public static final String AUTHENTICATION_ROUTE = "/auth";
    public static final String TOKEN_REFRESH_ROUTE = "/refresh";
    public static final String AUTHENTICATED_USER_ROUTE = "/user";
    public static final String RECOVERY_ROUTE = "/recover";
    public static final String PASSWORD_RESET_ROUTE = "/reset";
    public static final String ACTIVATION_ROUTE = "/activate";


    /**
     * REST Endpoints of the API
     */

    // Base endpoint of the API
    public static final String API_BASE_ENDPOINT = "/api";

    // User account and profile endpoints
    public static final String USERS_ENDPOINT = API_BASE_ENDPOINT + "/users/";
    public static final String USER_ENDPOINT = USERS_ENDPOINT + "/{id}";
    public static final String USER_SAVE = USERS_ENDPOINT + "save";

    //Books endpoint of the API
    public static final String BOOKS_ENDPOINT = API_BASE_ENDPOINT + "/books/";
    public static final String BOOKS_SAVE_ENDPOINT = BOOKS_ENDPOINT + "save";
    public static final String BOOKS_DELETE_ENDPOINT = BOOKS_ENDPOINT + "delete/{id}";
}
