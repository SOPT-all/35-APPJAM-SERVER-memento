package com.official.memento.member.api.dto

data class SignInRequest(
    // 플랫폼에 해당하는 로그인할 수 있는 Token
    val socialToken: String,
    // 어떤 소셜 플랫폼인지
    val provider: String
)
