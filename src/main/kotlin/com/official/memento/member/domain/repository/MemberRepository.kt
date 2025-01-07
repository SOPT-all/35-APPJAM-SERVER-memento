package com.official.memento.member.domain.repository

import com.official.memento.member.domain.Member

interface MemberRepository {
    fun save(member: Member)
}