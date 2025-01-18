package com.official.memento.auth.infrastructure;

import com.official.memento.auth.domain.AuthProvider;
import com.official.memento.auth.domain.port.AuthRepository;
import com.official.memento.member.infrastructure.persistence.MemberAuthEntity;
import com.official.memento.member.infrastructure.persistence.MemberAuthJpaRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuthRepositoryAdapter implements AuthRepository {

    private final MemberAuthJpaRepository memberAuthJpaRepository;

    public AuthRepositoryAdapter(final MemberAuthJpaRepository memberAuthJpaRepository) {
        this.memberAuthJpaRepository = memberAuthJpaRepository;
    }

    @Override
    public MemberAuthEntity save(final MemberAuthEntity authEntity) {
        return memberAuthJpaRepository.save(authEntity);
    }

    @Override
    public Optional<MemberAuthEntity> findByPlatformIdAndProvider(final String platformId, final AuthProvider provider) {
        return memberAuthJpaRepository.findByPlatformIdAndProvider(platformId, provider);
    }
}
