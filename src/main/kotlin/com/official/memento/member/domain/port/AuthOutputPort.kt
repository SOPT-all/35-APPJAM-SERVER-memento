package com.official.memento.member.domain.port

interface AuthOutputPort {
    fun login(provider: String, token: String) : String
}