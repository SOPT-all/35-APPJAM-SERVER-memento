package com.official.memento.member.api

import com.official.memento.member.api.dto.SignInRequest
import com.official.memento.member.api.dto.SignInResponse
import com.official.memento.member.application.SocialLoginUseCase
import com.official.memento.member.application.command.SocialLoginCommand
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class MemberApi(
    private val socialLoginUseCase: SocialLoginUseCase
) {

    @PostMapping("/sign-in")
    fun signIn(
        @RequestBody request: SignInRequest
    ) : ResponseEntity<SignInResponse> {
        val command = SocialLoginCommand(
            socialToken = request.socialToken,
            provider = request.provider
        )
        val token = socialLoginUseCase.signIn(command)
        return ResponseEntity.ok(SignInResponse(token))
    }
}