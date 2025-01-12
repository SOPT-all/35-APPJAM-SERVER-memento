package com.official.memento.member.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberPersonalInfoJpaRepository extends JpaRepository<MemberPersonalInfoEntity, Long> {
}
