package com.official.memento.member.infrastructure.persistence;

import com.official.memento.auth.domain.AuthProvider;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberAuthJpaRepository extends JpaRepository<MemberAuthEntity, Long> {
    Optional<MemberAuthEntity> findByPlatformIdAndProvider(final String platformId, final AuthProvider provider);
}
