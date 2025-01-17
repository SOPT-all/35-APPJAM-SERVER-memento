package com.official.memento.auth.controller.dto;

import jakarta.validation.constraints.NotBlank;

public record AuthApiRequest(
        @NotBlank String provider, // 소셜 로그인 제공자 (google, apple 등)
        @NotBlank String idToken // 클라한테 받은 ID 토큰
) {
}