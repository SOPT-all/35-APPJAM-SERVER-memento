package com.official.memento.member.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberPersonalInfoEntityJpaRepository extends JpaRepository<MemberPersonalInfoEntity, Long> {
    Optional<MemberPersonalInfoEntity> findByMemberId(final Long memberId);
}
