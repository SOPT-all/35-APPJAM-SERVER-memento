package com.official.memento.auth.service;

import com.official.memento.auth.domain.AuthorizationMember;

public class AuthResult {
    private final String accessToken;
    private final AuthorizationMember authorizationMember;

    public AuthResult(String accessToken, AuthorizationMember authorizationMember) {
        this.accessToken = accessToken;
        this.authorizationMember = authorizationMember;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public AuthorizationMember getAuthorizationMember() {
        return authorizationMember;
    }
}