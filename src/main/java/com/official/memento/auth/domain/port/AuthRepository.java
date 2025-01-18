package com.official.memento.auth.domain.port;

import com.official.memento.auth.domain.AuthProvider;
import com.official.memento.auth.domain.AuthorizationMember;
import com.official.memento.member.infrastructure.persistence.MemberAuthEntity;
import java.util.Optional;

public interface AuthRepository {
    MemberAuthEntity save(MemberAuthEntity authEntity);
    Optional<MemberAuthEntity> findByPlatformIdAndProvider(String platformId, AuthProvider provider);
}
