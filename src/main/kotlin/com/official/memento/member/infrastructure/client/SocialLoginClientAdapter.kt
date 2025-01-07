package com.official.memento.member.infrastructure.client

import com.official.memento.member.domain.port.AuthOutputPort
import com.official.memento.member.infrastructure.Adapter

@Adapter
class SocialLoginClientAdapter : AuthOutputPort {
    override fun login(provider: String, token: String) : String {
        if (provider == "구글") {
            // 구글 정보 받아오기
        }
        else if (provider == "애플") {
            // 애플 정보 받아오기
        }
        return "소셜로그인정보"
    }
}