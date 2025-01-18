package com.official.memento.member.infrastructure.persistence;

import com.official.memento.auth.domain.AuthProvider;
import jakarta.persistence.*;

@Entity
@Table(name = "member_auth", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"platformId", "provider"})})
public class MemberAuthEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private MemberEntity member;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private AuthProvider provider;

    @Column(nullable = false)
    private String platformId;

    @Column(nullable = false)
    private String refreshToken;

    protected MemberAuthEntity() {
    }

    public MemberAuthEntity(
            final Long id,
            final AuthProvider provider,
            final String platformId,
            final String refreshToken,
            final MemberEntity member) {
        this.id = id;
        this.provider = provider;
        this.platformId = platformId;
        this.refreshToken = refreshToken;
        this.member = member;
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

    public MemberEntity getMember() {
        return member;
    }
    public void updateRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public void setMember(MemberEntity member) {
        this.member = member;
    }
}
