package com.official.memento.member.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberPersonalInfoEntityJpaRepository extends JpaRepository<MemberPersonalInfoEntity, Long> {
    MemberPersonalInfoEntity findByMemberId(final Long memberId);
}
