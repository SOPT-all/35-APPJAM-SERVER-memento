package com.official.memento.auth.infrastructure.apple;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.official.memento.auth.domain.port.AuthClientOutputPort;
import com.official.memento.global.exception.ErrorCode;
import com.official.memento.global.exception.MementoException;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.RSAPublicKeySpec;
import java.util.Base64;
import java.util.Map;

@Component
public class AppleAuthClientAdapter implements AuthClientOutputPort {

    private static final String APPLE_KEYS_URL = "https://appleid.apple.com/auth/keys";
    private final RestClient restClient;
    private final ObjectMapper objectMapper;

    public AppleAuthClientAdapter(RestClient restClient, ObjectMapper objectMapper) {
        this.restClient = restClient;
        this.objectMapper = objectMapper;
    }

    @Override
    public Map<String, Object> verifyIdToken(final String idToken) {
        String[] tokenParts = idToken.split("\\.");
        if (tokenParts.length != 3) {
            throw new MementoException(ErrorCode.INVALID_ID_TOKEN);
        }

        String headerJson = decodeBase64(tokenParts[0]);
        String payloadJson = decodeBase64(tokenParts[1]);

        Map<String, String> header = parseJson(headerJson, Map.class);
        Map<String, Object> payload = parseJson(payloadJson, Map.class);

        String kid = header.get("kid");
        String alg = header.get("alg");

        AppleKeysResponse keysResponse = fetchApplePublicKeys();
        AppleKey matchingKey = findMatchingKey(keysResponse, kid, alg);
        PublicKey publicKey = generatePublicKey(matchingKey);

        Jwts.parserBuilder()
                .setSigningKey(publicKey)
                .build()
                .parseClaimsJws(idToken);

        return payload;
    }

    private String decodeBase64(String encoded) {
        return new String(Base64.getUrlDecoder().decode(encoded));
    }

    private AppleKeysResponse fetchApplePublicKeys() {
        return restClient.get()
                .uri(APPLE_KEYS_URL)
                .retrieve()
                .body(AppleKeysResponse.class);
    }

    private AppleKey findMatchingKey(AppleKeysResponse keysResponse, String kid, String alg) {
        return keysResponse.getKeys().stream()
                .filter(key -> key.getKid().equals(kid) && key.getAlg().equals(alg))
                .findFirst()
                .orElseThrow(() -> new MementoException(ErrorCode.INVALID_ID_TOKEN));
    }

    private PublicKey generatePublicKey(AppleKey key) {
        BigInteger n = new BigInteger(1, Base64.getUrlDecoder().decode(key.getN()));
        BigInteger e = new BigInteger(1, Base64.getUrlDecoder().decode(key.getE()));
        try {
            return KeyFactory.getInstance("RSA")
                    .generatePublic(new RSAPublicKeySpec(n, e));
        } catch (Exception ex) {
            throw new MementoException(ErrorCode.INVALID_ID_TOKEN);
        }
    }

    private <T> T parseJson(String json, Class<T> valueType) {
        try {
            return objectMapper.readValue(json, valueType);
        } catch (Exception ex) {
            throw new MementoException(ErrorCode.INVALID_ID_TOKEN);
        }
    }
    }
