package com.official.memento.auth.infrastructure.jwt;

import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Base64;

public class SecretKeyGenerator {

    public static void main(String[] args) {
        final Key key = Keys.secretKeyFor(io.jsonwebtoken.SignatureAlgorithm.HS256); // HS256 알고리즘용 Secret Key 생성
        final String secretKey = Base64.getEncoder().encodeToString(key.getEncoded()); // Base64 인코딩
        System.out.println("Generated Secret Key: " + secretKey);
    }
}
