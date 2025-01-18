package com.official.memento.member.infrastructure;

import com.official.memento.global.exception.EntityNotFoundException;
import com.official.memento.global.exception.ErrorCode;
import com.official.memento.global.stereotype.Adapter;
import com.official.memento.member.domain.MemberPersonalInfo;
import com.official.memento.member.domain.port.MemberPersonalInfoRepository;
import com.official.memento.member.domain.port.MemberRepository;
import com.official.memento.member.infrastructure.persistence.MemberPersonalInfoEntity;
import com.official.memento.member.infrastructure.persistence.MemberPersonalInfoEntityJpaRepository;
import com.official.memento.member.infrastructure.persistence.MemberPersonalInfoMapper;

@Adapter
public class MemberPersonalInfoRepositoryAdapter implements MemberPersonalInfoRepository {
    private final MemberPersonalInfoEntityJpaRepository memberPersonalInfoEntityJpaRepository;
    private final MemberRepository memberRepository;


    public MemberPersonalInfoRepositoryAdapter(
            final MemberPersonalInfoEntityJpaRepository memberPersonalInfoEntityJpaRepository,
            final MemberRepository memberRepository) {
        this.memberPersonalInfoEntityJpaRepository = memberPersonalInfoEntityJpaRepository;
        this.memberRepository = memberRepository;
    }

    @Override
    public MemberPersonalInfo findByMemberId(final Long memberId) {
        MemberPersonalInfoEntity entity = memberPersonalInfoEntityJpaRepository.findByMemberId(memberId)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.NOT_FOUND_ENTITY));
        return MemberPersonalInfoMapper.toDomain(entity);
    }

    @Override
    public MemberPersonalInfo create(final MemberPersonalInfo memberPersonalInfo) {
        if (memberPersonalInfoEntityJpaRepository.findByMemberId(memberPersonalInfo.getMemberId()) != null) {
            throw new IllegalStateException("이미 존재하는 회원 정보입니다.");
        }
        MemberPersonalInfoEntity entityToSave = MemberPersonalInfoMapper.toEntity(memberPersonalInfo, memberRepository);
        MemberPersonalInfoEntity savedEntity = memberPersonalInfoEntityJpaRepository.save(entityToSave);
        return MemberPersonalInfoMapper.toDomain(savedEntity);
    }

    @Override
    public MemberPersonalInfo update(final MemberPersonalInfo memberPersonalInfo) {
        MemberPersonalInfoEntity entity = memberPersonalInfoEntityJpaRepository.findByMemberId(memberPersonalInfo.getMemberId())
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.NOT_FOUND_ENTITY));
        entity.updatePersonalInfo(
                memberPersonalInfo.getWakeUpTime(),
                memberPersonalInfo.getWindDownTime(),
                memberPersonalInfo.getJob(),
                memberPersonalInfo.getJobOtherDetail(),
                memberPersonalInfo.getIsStressedUnorganizedSchedule(),
                memberPersonalInfo.getIsForgetImportantThings(),
                memberPersonalInfo.getIsPreferReminder(),
                memberPersonalInfo.getIsImportantBreaks()
        );
        return MemberPersonalInfoMapper.toDomain(entity);
    }
}