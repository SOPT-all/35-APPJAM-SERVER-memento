package com.official.memento.auth.domain;

public class RefreshToken {
    private final String token;

    public RefreshToken(final String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
