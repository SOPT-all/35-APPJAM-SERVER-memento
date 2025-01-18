package com.official.memento.auth.service;

import com.official.memento.member.domain.port.MemberRepository;
import com.official.memento.auth.domain.AccessToken;
import com.official.memento.auth.domain.AuthProvider;
import com.official.memento.auth.domain.RefreshToken;
import com.official.memento.auth.domain.port.AuthClientOutputPort;
import com.official.memento.auth.domain.port.AuthRepository;
import com.official.memento.auth.service.command.AuthCommand;
import com.official.memento.auth.service.usecase.AuthUseCase;
import com.official.memento.global.exception.ErrorCode;
import com.official.memento.global.exception.MementoException;
import com.official.memento.member.infrastructure.persistence.MemberAuthEntity;
import com.official.memento.member.infrastructure.persistence.MemberEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
public class AuthService implements AuthUseCase {

    private final Map<AuthProvider, AuthClientOutputPort> authClientAdapters;
    private final AuthRepository authRepository;
    private final MemberRepository memberRepository;
    private final JwtUtil jwtUtil;

    public AuthService(
            Map<AuthProvider, AuthClientOutputPort> authClientAdapters,
            AuthRepository authRepository,
            MemberRepository memberRepository,
            JwtUtil jwtUtil
    ) {
        this.authClientAdapters = authClientAdapters;
        this.authRepository = authRepository;
        this.memberRepository = memberRepository;
        this.jwtUtil = jwtUtil;
    }

    @Override
    @Transactional
    public AuthResult authenticate(final AuthCommand command) {
        final AuthProvider provider = getAuthProvider(command.providerName());
        final Map<String, Object> tokenInfo = verifyIdToken(provider, command.idToken());

        final String platformId = (String) tokenInfo.get("sub");
        final String email = (String) tokenInfo.get("email");

        MemberAuthEntity authEntity = authRepository.findByPlatformIdAndProvider(platformId, provider)
                .orElseGet(() -> createNewMember(platformId, provider));

        boolean isNewUser = memberRepository.findPersonalInfoByMemberId(authEntity.getMember().getId()).isEmpty();

        AccessToken accessToken = jwtUtil.generateAccessToken(authEntity.getMember().getId().toString(), email);
        RefreshToken refreshToken = jwtUtil.generateRefreshToken(authEntity.getMember().getId().toString());

        authEntity.updateRefreshToken(refreshToken.getToken());
        authRepository.save(authEntity);

        return new AuthResult(accessToken, authEntity.getMember(), isNewUser);
    }

    private MemberAuthEntity createNewMember(String platformId, AuthProvider provider) {
        MemberEntity newMember = new MemberEntity();
        memberRepository.save(newMember);

        MemberAuthEntity newAuth = new MemberAuthEntity(null, provider, platformId, "", newMember);
        newMember.setAuth(newAuth);
        authRepository.save(newAuth);

        return newAuth;
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


