package com.official.memento.member.application

import com.official.memento.member.application.command.SocialLoginCommand
import com.official.memento.member.domain.Member
import com.official.memento.member.domain.port.AuthOutputPort
import com.official.memento.member.domain.repository.MemberRepository
import org.springframework.stereotype.Service

@Service
class SocialLoginService(
    private val authOutputPort: AuthOutputPort,
    private val memberRepository: MemberRepository
) : SocialLoginUseCase {

    override fun signIn(command: SocialLoginCommand) : String {
        // 비즈니스 로직.
        // 소셜로그인 정보
        val socialLoginInfo = authOutputPort.login(command.provider, command.socialToken)
        val member = Member(
            socialLoginInfo= socialLoginInfo
        )
        val memberId = memberRepository.save(member)

        // Token은 유저정보 대체재. 유저를 구분할 수 있는 값. -> PK / Unique Key
        // Token을 만든다.
        val token ="sampletoken"
        return token
    }
}