package com.sari.gamecorner.security;

class SecurityConstants {

    static final String SECRET = "SecretKeyToGenJWTs";
    static final long EXPIRATION_TIME = 100_000;
    static final String TOKEN_PREFIX = "Bearer ";
    static final String HEADER_STRING = "Authorization";
    static final String JOIN_URL = "/users/registration";
    static final String LOGIN_URL = "/users/login";
    static final String PUBLIC_URLS = "/games/**";

}
