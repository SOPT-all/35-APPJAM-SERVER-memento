package com.official.memento.auth.infrastructure;

import com.official.memento.auth.domain.port.AuthClientOutputPort;
import com.official.memento.global.dto.SuccessResponse;
import com.official.memento.global.exception.ErrorCode;
import com.official.memento.global.exception.MementoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
public class GoogleAuthClientAdapter implements AuthClientOutputPort {

    private final RestTemplate restTemplate;

    public GoogleAuthClientAdapter(final RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Map<String, Object> verifyIdToken(final String idToken) {
        final String tokenInfoUrl = "https://oauth2.googleapis.com/tokeninfo?id_token=" + idToken;
        final ResponseEntity<Map> response = restTemplate.getForEntity(tokenInfoUrl, Map.class);

        if (!response.getStatusCode().is2xxSuccessful() || response.getBody() == null) {
            throw new MementoException(ErrorCode.INVALID_ID_TOKEN);
        }

        SuccessResponse.of(HttpStatus.OK, "ID Token verified successfully", response.getBody());

        return response.getBody();
    }
}
