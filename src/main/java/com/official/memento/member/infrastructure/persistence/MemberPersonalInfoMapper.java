package com.official.memento.member.infrastructure.persistence;

import com.official.memento.global.exception.EntityNotFoundException;
import com.official.memento.global.exception.ErrorCode;
import com.official.memento.member.domain.MemberPersonalInfo;
import com.official.memento.member.domain.port.MemberRepository;

public class MemberPersonalInfoMapper {
    public static MemberPersonalInfo toDomain(final MemberPersonalInfoEntity entity) {
        return MemberPersonalInfo.of(
                entity.getMember().getId(),
                entity.getWakeUpTime(),
                entity.getWindDownTime(),
                entity.getJob(),
                entity.getJobOtherDetail(),
                entity.getIsStressedUnorganizedSchedule(),
                entity.getIsForgetImportantThings(),
                entity.getIsPreferReminder(),
                entity.getIsImportantBreaks());
    }

    public static MemberPersonalInfoEntity toEntity(final MemberPersonalInfo domain, MemberRepository memberRepository) {
        return MemberPersonalInfoEntity.of(
                memberRepository.findById(domain.getMemberId()).orElseThrow(() -> new EntityNotFoundException(ErrorCode.NOT_FOUND_ENTITY)),
                domain.getWakeUpTime(),
                domain.getWindDownTime(),
                domain.getJob(),
                domain.getJobOtherDetail(),
                domain.getIsStressedUnorganizedSchedule(),
                domain.getIsForgetImportantThings(),
                domain.getIsPreferReminder(),
                domain.getIsImportantBreaks());
    }
}