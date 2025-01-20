package com.official.memento.member.infrastructure;

import com.official.memento.global.stereotype.Adapter;
import com.official.memento.member.domain.port.MemberRepository;
import com.official.memento.member.infrastructure.persistence.MemberEntity;
import com.official.memento.member.infrastructure.persistence.MemberJpaRepository;
import com.official.memento.member.infrastructure.persistence.MemberPersonalInfoEntity;
import com.official.memento.member.infrastructure.persistence.MemberPersonalInfoEntityJpaRepository;

import java.util.Optional;

@Adapter
public class MemberRepositoryAdapter implements MemberRepository {
    private final MemberJpaRepository memberJpaRepository;
    private final MemberPersonalInfoEntityJpaRepository personalInfoRepository;

    public MemberRepositoryAdapter(MemberJpaRepository memberJpaRepository, MemberPersonalInfoEntityJpaRepository personalInfoRepository) {
        this.memberJpaRepository = memberJpaRepository;
        this.personalInfoRepository = personalInfoRepository;
    }

    @Override
    public MemberEntity save(MemberEntity member) {
        return memberJpaRepository.save(member);
    }

    @Override
    public Optional<MemberEntity> findById(Long id) {
        return memberJpaRepository.findById(id);
    }

    @Override
    public Optional<MemberPersonalInfoEntity> findPersonalInfoByMemberId(Long memberId) {
        return personalInfoRepository.findByMemberId(memberId);
    }


}
