package com.official.memento.member.infrastructure.persistence;

import com.official.memento.auth.domain.AuthProvider;
import jakarta.persistence.*;

@Entity
@Table(name = "member_auth")
public class MemberAuthEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final Long id;

    @Enumerated(EnumType.STRING)
    private final AuthProvider provider;

    private final String platformId;

    private final String refreshToken;

    private final Long memberId;

    protected MemberAuthEntity() {
        this.id = null;
        this.provider = null;
        this.platformId = null;
        this.refreshToken = null;
        this.memberId = null;
    }

    public MemberAuthEntity(
            final Long id,
            final AuthProvider provider,
            final String platformId,
            final String refreshToken,
            final Long memberId
    ) {
        this.id = id;
        this.provider = provider;
        this.platformId = platformId;
        this.refreshToken = refreshToken;
        this.memberId = memberId;
    }

    public Long getId() {
        return id;
    }

    public AuthProvider getProvider() {
        return provider;
    }

    public String getPlatformId() {
        return platformId;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public Long getMemberId() {
        return memberId;
    }
}
