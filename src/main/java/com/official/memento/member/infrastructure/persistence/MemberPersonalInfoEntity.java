package com.official.memento.member.infrastructure.persistence;


import jakarta.persistence.*;

@Entity
@Table(name = "member_personal_info")
public class MemberPersonalInfoEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long memberId;
}
