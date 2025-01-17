package com.official.memento.auth.service;

import com.official.memento.auth.domain.AccessToken;
import com.official.memento.auth.domain.AuthorizationMember;

public class AuthResult {
    private final AccessToken accessToken;
    private final AuthorizationMember authorizationMember;

    public AuthResult(AccessToken accessToken, AuthorizationMember authorizationMember) {
        this.accessToken = accessToken;
        this.authorizationMember = authorizationMember;
    }

    public AccessToken getAccessToken() {
        return accessToken;
    }

    public AuthorizationMember getAuthorizationMember() {
        return authorizationMember;
    }
}