package com.official.memento.member.infrastructure.persistence

import org.springframework.data.jpa.repository.JpaRepository

interface MemberJpaRepository : JpaRepository<MemberEntity, Long>{
}