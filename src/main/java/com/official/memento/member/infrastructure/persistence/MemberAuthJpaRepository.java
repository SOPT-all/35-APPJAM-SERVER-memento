package com.official.memento.member.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import com.official.memento.member.infrastructure.persistence.MemberAuthEntity;
import com.official.memento.auth.domain.AuthProvider;

import java.util.Optional;

public interface MemberAuthJpaRepository extends JpaRepository<MemberAuthEntity, Long> {
    Optional<MemberAuthEntity> findByPlatformIdAndProvider(String platformId, AuthProvider provider);
}
