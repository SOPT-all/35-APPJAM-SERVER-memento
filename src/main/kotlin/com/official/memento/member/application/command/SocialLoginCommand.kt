package com.official.memento.member.application.command

data class SocialLoginCommand(
    val socialToken: String,
    val provider: String
)
