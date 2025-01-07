package com.official.memento.member.infrastructure.persistence

import com.official.memento.member.domain.Member
import com.official.memento.member.domain.repository.MemberRepository
import com.official.memento.member.infrastructure.Adapter

@Adapter
class MemberRepositoryAdapter(
    private val memberJpaRepository: MemberJpaRepository
) : MemberRepository {
    override fun save(member: Member) {
        // member를 MemberEntity로 바꿔주고, save
        memberJpaRepository.save(
            MemberEntity(
                id = member.id,
                name = member.name,
                socialLoginInfo = member.socialLoginInfo
            )
        )
    }
}