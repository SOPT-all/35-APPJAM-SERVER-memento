package com.official.memento.member.application

import com.official.memento.member.application.command.SocialLoginCommand

@FunctionalInterface
interface SocialLoginUseCase {
    fun signIn(command: SocialLoginCommand) : String
}