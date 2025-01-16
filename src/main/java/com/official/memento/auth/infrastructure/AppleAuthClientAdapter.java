package com.official.memento.auth.infrastructure;

import com.official.memento.auth.domain.port.AuthClientOutputPort;
import com.official.memento.global.exception.ErrorCode;
import com.official.memento.global.exception.MementoException;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class AppleAuthClientAdapter implements AuthClientOutputPort {

    @Override
    public Map<String, Object> verifyIdToken(final String idToken) {
        // TODO: 애플 인증 로직 구현
        // 임시로 예외 던지기
        throw new MementoException(ErrorCode.NOT_FOUND_ENTITY);
    }
}
