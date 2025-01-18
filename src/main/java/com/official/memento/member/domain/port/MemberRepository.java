package com.official.memento.member.domain.port;

import com.official.memento.member.infrastructure.persistence.MemberEntity;
import com.official.memento.member.infrastructure.persistence.MemberPersonalInfoEntity;

import java.util.Optional;

public interface MemberRepository {
    MemberEntity save(MemberEntity member); // MemberEntity 저장
    Optional<MemberEntity> findById(Long id); // ID로 MemberEntity 조회
    Optional<MemberPersonalInfoEntity> findPersonalInfoByMemberId(Long memberId);
}
