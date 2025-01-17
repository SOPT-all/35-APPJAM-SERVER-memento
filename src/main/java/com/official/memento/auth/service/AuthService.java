package com.official.memento.auth.service;

import com.official.memento.auth.domain.AuthProvider;
import com.official.memento.auth.domain.AuthorizationMember;
import com.official.memento.auth.domain.port.AuthClientOutputPort;
import com.official.memento.auth.domain.port.AuthRepository;
import com.official.memento.auth.service.command.AuthCommand;
import com.official.memento.auth.service.usecase.AuthUseCase;
import com.official.memento.global.exception.ErrorCode;
import com.official.memento.global.exception.MementoException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
public class AuthService implements AuthUseCase {

    private final Map<AuthProvider, AuthClientOutputPort> authClientAdapters;
    private final AuthRepository authRepository;
    private final JwtUtil jwtUtil;

    public AuthService(
            Map<AuthProvider, AuthClientOutputPort> authClientAdapters,
            AuthRepository authRepository,
            JwtUtil jwtUtil
    ) {
        this.authClientAdapters = authClientAdapters;
        this.authRepository = authRepository;
        this.jwtUtil = jwtUtil;
    }

    @Override
    @Transactional
    public AuthResult authenticate(final AuthCommand command) {
        final AuthProvider provider = getAuthProvider(command.providerName());
        final Map<String, Object> tokenInfo = verifyIdToken(provider, command.idToken());
        final String userId = (String) tokenInfo.get("sub");
        final String email = (String) tokenInfo.get("email");

        AuthorizationMember member = authRepository.findByPlatformIdAndProvider(userId, provider);

        final boolean isNewUser = (member == null); // new user 여부
        if (isNewUser) {
            member = AuthorizationMember.of(userId, provider, null, true); // isNewUser = true
            authRepository.save(member);
        } else {
            member = AuthorizationMember.of(userId, provider, member.getRefreshToken(), false); // isNewUser = false
        }

        final String accessToken = jwtUtil.generateAccessToken(userId, email);
        final String refreshToken = jwtUtil.generateRefreshToken(userId);

        member = AuthorizationMember.of(userId, provider, refreshToken, isNewUser);

        return new AuthResult(accessToken, member);
    }

    private AuthProvider getAuthProvider(final String providerName) {
        try {
            return AuthProvider.valueOf(providerName.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new MementoException(ErrorCode.UNSUPPORTED_PROVIDER);
        }
    }

    private Map<String, Object> verifyIdToken(final AuthProvider provider, final String idToken) {
        final AuthClientOutputPort clientAdapter = authClientAdapters.get(provider);
        if (clientAdapter == null) {
            throw new MementoException(ErrorCode.UNSUPPORTED_PROVIDER);
        }
        try {
            return clientAdapter.verifyIdToken(idToken);
        } catch (Exception e) {
            throw new MementoException(ErrorCode.INVALID_ID_TOKEN);
        }
    }
}

