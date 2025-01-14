package com.official.memento.member.infrastructure.persistence;

import com.official.memento.member.domain.MemberPersonalInfo;

public class MemberPersonalInfoMapper {
    public static MemberPersonalInfo toDomain(MemberPersonalInfoEntity entity) {
        return MemberPersonalInfo.of(
                entity.getMemberId(),
                entity.getWakeUpTime(),
                entity.getWindDownTime(),
                entity.getJob(),
                entity.getJobOtherDetail(),
                entity.getIsStressedUnorganizedSchedule(),
                entity.getIsForgetImportantThings(),
                entity.getIsPreferReminder(),
                entity.getIsImportantBreaks()
        );
    }

    public static MemberPersonalInfoEntity toEntity(MemberPersonalInfo domain) {
        return MemberPersonalInfoEntity.of(
                domain.getMemberId(),
                domain.getWakeUpTime(),
                domain.getWindDownTime(),
                domain.getJob(),
                domain.getJobOtherDetail(),
                domain.getIsStressedUnorganizedSchedule(),
                domain.getIsForgetImportantThings(),
                domain.getIsPreferReminder(),
                domain.getIsImportantBreaks()
        );
    }
}