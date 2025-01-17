package com.official.memento.member.infrastructure.persistence;

import com.official.memento.global.entity.BaseTimeEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "member")
public class MemberEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
