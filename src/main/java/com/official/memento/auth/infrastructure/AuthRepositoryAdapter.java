package com.official.memento.auth.infrastructure;

import com.official.memento.auth.domain.AuthorizationMember;
import com.official.memento.auth.domain.AuthProvider;
import com.official.memento.auth.domain.port.AuthRepository;
import com.official.memento.global.exception.ErrorCode;
import com.official.memento.global.exception.MementoException;
import com.official.memento.member.infrastructure.persistence.MemberAuthEntity;
import com.official.memento.member.infrastructure.persistence.MemberAuthJpaRepository;
import org.springframework.stereotype.Component;

@Component
public class AuthRepositoryAdapter implements AuthRepository {

    private final MemberAuthJpaRepository memberAuthJpaRepository;

    public AuthRepositoryAdapter(final MemberAuthJpaRepository memberAuthJpaRepository) {
        this.memberAuthJpaRepository = memberAuthJpaRepository;
    }

    @Override
    public AuthorizationMember save(final AuthorizationMember member) {
        // MemberAuthEntity 생성 시 필요한 값 초기화
        final MemberAuthEntity entity = new MemberAuthEntity(
                null, // ID는 JPA에서 자동 생성
                member.getProvider(),
                member.getPlatformId(),
                member.getRefreshToken(),
                null // memberId는 사용하지 않으므로 null
        );
        final MemberAuthEntity savedEntity = memberAuthJpaRepository.save(entity);

        // 저장된 엔티티를 기반으로 AuthorizationMember 반환
        return AuthorizationMember.of(
                savedEntity.getPlatformId(),
                savedEntity.getProvider(),
                savedEntity.getRefreshToken(),
                true // 새 사용자 등록이므로 isNewUser = true
        );
    }

    @Override
    public AuthorizationMember findById(final String id) {
        return memberAuthJpaRepository.findById(Long.parseLong(id))
                .map(entity -> AuthorizationMember.of(
                        entity.getPlatformId(),
                        entity.getProvider(),
                        entity.getRefreshToken(),
                        false // 기존 사용자는 isNewUser = false
                ))
                .orElseThrow(() -> new MementoException(ErrorCode.NOT_FOUND_ENTITY));
    }

    @Override
    public AuthorizationMember findByPlatformIdAndProvider(final String platformId, final AuthProvider provider) {
        return memberAuthJpaRepository.findByPlatformIdAndProvider(platformId, provider)
                .map(entity -> AuthorizationMember.of(
                        entity.getPlatformId(),
                        entity.getProvider(),
                        entity.getRefreshToken(),
                        false // 기존 사용자는 isNewUser = false
                ))
                .orElseThrow(() -> new MementoException(ErrorCode.NOT_FOUND_ENTITY));
    }
}
