package com.official.memento.auth.domain;

public class AuthorizationMember {
    private final String platformId;
    private final AuthProvider provider;
    private final RefreshToken refreshToken;
    private final boolean isNewUser;

    public static AuthorizationMember of(final String platformId, final AuthProvider provider, final RefreshToken refreshToken, final boolean isNewUser) {
        return new AuthorizationMember(platformId, provider, refreshToken, isNewUser);
    }

    private AuthorizationMember(final String platformId, final AuthProvider provider, final RefreshToken refreshToken, final boolean isNewUser) {
        this.platformId = platformId;
        this.provider = provider;
        this.refreshToken = refreshToken;
        this.isNewUser = isNewUser;
    }

    public String getPlatformId() {
        return platformId;
    }

    public AuthProvider getProvider() {
        return provider;
    }

    public RefreshToken getRefreshToken() {
        return refreshToken;
    }

    public boolean isNewUser() {
        return isNewUser;
    }
}


