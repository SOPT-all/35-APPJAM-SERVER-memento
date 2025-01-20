package com.official.memento.auth.service;

import com.official.memento.auth.domain.AccessToken;
import com.official.memento.auth.domain.AuthorizationMember;
import com.official.memento.member.infrastructure.persistence.MemberEntity;

public class AuthResult {
    private final AccessToken accessToken;
    private final MemberEntity member;
    private final boolean isNewUser;

    public AuthResult(AccessToken accessToken, MemberEntity member, boolean isNewUser) {
        this.accessToken = accessToken;
        this.member = member;
        this.isNewUser = isNewUser;
    }

    public AccessToken getAccessToken() {
        return accessToken;
    }

    public MemberEntity getMember() {
        return member;
    }

    public boolean isNewUser() {
        return isNewUser;
    }
}