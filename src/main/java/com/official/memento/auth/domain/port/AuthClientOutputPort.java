package com.official.memento.auth.domain.port;

import java.util.Map;

public interface AuthClientOutputPort {
    Map<String, Object> verifyIdToken(String idToken);
}
