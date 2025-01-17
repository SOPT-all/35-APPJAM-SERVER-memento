package com.official.memento.auth.domain;

public class AccessToken {
    private String token;

    public AccessToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
