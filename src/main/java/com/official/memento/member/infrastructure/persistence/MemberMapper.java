package com.official.memento.member.infrastructure.persistence;

import com.official.memento.member.domain.Member;

public class MemberMapper {

     public static MemberEntity toEntity(Member member) {
            return new MemberEntity(member.getId(), member.getNickname());
     }

     public static Member toDomain(MemberEntity memberEntity) {
                return new Member(memberEntity.getId(), memberEntity.getNickname());
     }
}
