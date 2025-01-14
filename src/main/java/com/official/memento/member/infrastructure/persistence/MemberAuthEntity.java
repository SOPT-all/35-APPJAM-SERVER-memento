package com.official.memento.member.infrastructure.persistence;

import com.official.memento.auth.domain.AuthProvider;
import jakarta.persistence.*;

@Entity
@Table(name = "member_auth")
public class MemberAuthEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private AuthProvider provider;

    private String platformId;

    private String refreshToken;

    private Long memberId;
}
