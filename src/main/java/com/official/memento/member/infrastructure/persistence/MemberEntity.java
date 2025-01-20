package com.official.memento.member.infrastructure.persistence;

import com.official.memento.global.entity.BaseTimeEntity;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "member")
public class MemberEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @OneToOne(mappedBy = "member", cascade = CascadeType.ALL)
    private MemberAuthEntity auth;

    @OneToOne(mappedBy = "member", cascade = CascadeType.ALL)
    private MemberPersonalInfoEntity personalInfo;

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public MemberPersonalInfoEntity getPersonalInfo() {
        return personalInfo;
    }

    public MemberAuthEntity getAuth() {
        return auth;
    }

    public void setAuth(MemberAuthEntity auth) {
        this.auth = auth;
        if (auth != null) {
            auth.setMember(this);
        }
    }
}
