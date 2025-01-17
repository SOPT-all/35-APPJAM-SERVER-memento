package com.official.memento.auth.infrastructure.jwt;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtConfig {

    @Value("${JWT.SECRET}")
    private String secret;

    @Value("${JWT.ACCESS_TOKEN_EXPIRATION}")
    private long accessTokenExpiration;

    @Value("${JWT.REFRESH_TOKEN_EXPIRATION}")
    private long refreshTokenExpiration;

    @Bean
    public JwtUtil jwtUtil() {
        final JwtUtil jwtUtil = new JwtUtil(secret, accessTokenExpiration, refreshTokenExpiration);
        return jwtUtil;
    }
}