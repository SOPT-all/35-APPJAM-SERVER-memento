package com.official.memento.auth.domain.port;

import com.official.memento.auth.domain.AuthProvider;
import com.official.memento.auth.domain.AuthorizationMember;

public interface AuthRepository {
    AuthorizationMember save(AuthorizationMember member);
    AuthorizationMember findById(String id);
    AuthorizationMember findByPlatformIdAndProvider(String platformId, AuthProvider provider);
}
