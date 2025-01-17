package com.official.memento.auth.domain;

public class RefreshToken {
    private final String refreshToken;

    public RefreshToken(final String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }
}
