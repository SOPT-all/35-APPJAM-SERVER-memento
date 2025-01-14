package com.official.memento.member.infrastructure;

import com.official.memento.global.stereotype.Adapter;
import com.official.memento.member.domain.MemberPersonalInfo;
import com.official.memento.member.domain.port.MemberPersonalInfoRepository;
import com.official.memento.member.infrastructure.persistence.MemberPersonalInfoEntity;
import com.official.memento.member.infrastructure.persistence.MemberPersonalInfoJpaRepository;

@Adapter
public class MemberPersonalInfoRepositoryAdapter implements MemberPersonalInfoRepository {
    private final MemberPersonalInfoJpaRepository memberPersonalInfoJpaRepository;

    public MemberPersonalInfoRepositoryAdapter(MemberPersonalInfoJpaRepository memberPersonalInfoJpaRepository) {
        this.memberPersonalInfoJpaRepository = memberPersonalInfoJpaRepository;
    }

    @Override
    public MemberPersonalInfo findByMemberId(Long memberId) {
        MemberPersonalInfoEntity entity = memberPersonalInfoJpaRepository.findByMemberId(memberId);
        if (entity == null) {
            return null;
        }
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

    @Override
    public MemberPersonalInfo save(MemberPersonalInfo memberPersonalInfo) {
        // 기존 엔티티 찾기
        MemberPersonalInfoEntity existingEntity = memberPersonalInfoJpaRepository.findByMemberId(memberPersonalInfo.getMemberId());

        MemberPersonalInfoEntity entityToSave;
        if (existingEntity != null) {
            // 기존 엔티티가 있다면 업데이트
            entityToSave = MemberPersonalInfoEntity.of(
                    memberPersonalInfo.getMemberId(),
                    memberPersonalInfo.getWakeUpTime(),
                    memberPersonalInfo.getWindDownTime(),
                    memberPersonalInfo.getJob(),
                    memberPersonalInfo.getJobOtherDetail(),
                    memberPersonalInfo.getIsStressedUnorganizedSchedule(),
                    memberPersonalInfo.getIsForgetImportantThings(),
                    memberPersonalInfo.getIsPreferReminder(),
                    memberPersonalInfo.getIsImportantBreaks()
            );
            // 기존 id 설정
            entityToSave.setId(existingEntity.getId());
        } else {
            // 없다먼 새로운 엔티티 생성
            entityToSave = MemberPersonalInfoEntity.of(
                    memberPersonalInfo.getMemberId(),
                    memberPersonalInfo.getWakeUpTime(),
                    memberPersonalInfo.getWindDownTime(),
                    memberPersonalInfo.getJob(),
                    memberPersonalInfo.getJobOtherDetail(),
                    memberPersonalInfo.getIsStressedUnorganizedSchedule(),
                    memberPersonalInfo.getIsForgetImportantThings(),
                    memberPersonalInfo.getIsPreferReminder(),
                    memberPersonalInfo.getIsImportantBreaks()
            );
        }

        MemberPersonalInfoEntity savedEntity = memberPersonalInfoJpaRepository.save(entityToSave);
        return MemberPersonalInfo.of(
                savedEntity.getMemberId(),
                savedEntity.getWakeUpTime(),
                savedEntity.getWindDownTime(),
                savedEntity.getJob(),
                savedEntity.getJobOtherDetail(),
                savedEntity.getIsStressedUnorganizedSchedule(),
                savedEntity.getIsForgetImportantThings(),
                savedEntity.getIsPreferReminder(),
                savedEntity.getIsImportantBreaks()
        );
    }
}