package com.official.memento.auth.controller.dto;

public record AuthApiResponse(
        String message, // 로그인 성공 메시지
        String accessToken, // 발급된 Access Token
        String refreshToken, // 발급된 Refresh Token
        boolean isNewUser // 신규 사용자 여부
) {
}