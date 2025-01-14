package com.official.memento.member.service;

import com.official.memento.member.controller.dto.MemberResponse;
import com.official.memento.member.domain.MemberPersonalInfo;
import com.official.memento.member.domain.port.MemberPersonalInfoRepository;
import com.official.memento.member.service.command.MemberUpdateCommand;
import com.official.memento.member.service.usecase.MemberUpdateUseCase;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MemberService implements MemberUpdateUseCase {
    private final MemberPersonalInfoRepository memberPersonalInfoRepository;

    public MemberService(MemberPersonalInfoRepository memberPersonalInfoRepository) {
        this.memberPersonalInfoRepository = memberPersonalInfoRepository;
    }

    @Override
    @Transactional
    public MemberResponse updatePersonalInfo(MemberUpdateCommand command) {
        //기존 멤버 정보를 memberId로 조회
        MemberPersonalInfo existingInfo = memberPersonalInfoRepository.findByMemberId(command.memberId());

        if (existingInfo == null) {
            // 새로운 데이터 생성하고 저장
            MemberPersonalInfo newInfo = MemberPersonalInfo.of(
                    command.memberId(),
                    command.wakeUpTime(),
                    command.windDownTime(),
                    command.job(),
                    command.jobOtherDetail(),
                    command.isStressedUnorganizedSchedule(),
                    command.isForgetImportantThings(),
                    command.isPreferReminder(),
                    command.isImportantBreaks()
            );
            MemberPersonalInfo savedInfo = memberPersonalInfoRepository.save(newInfo);
            return convertToResponse(savedInfo);
        }

        // 기존 정보가 있으면 업데이트
        existingInfo.update(
                command.wakeUpTime(),
                command.windDownTime(),
                command.job(),
                command.jobOtherDetail(),
                command.isStressedUnorganizedSchedule(),
                command.isForgetImportantThings(),
                command.isPreferReminder(),
                command.isImportantBreaks()
        );

        MemberPersonalInfo savedInfo = memberPersonalInfoRepository.save(existingInfo);
        return convertToResponse(savedInfo);
    }

    // MemberPersonalInfo -> MemberResponse로
    private MemberResponse convertToResponse(MemberPersonalInfo info) {
        return new MemberResponse(
                info.getMemberId(),
                info.getWakeUpTime(),
                info.getWindDownTime(),
                info.getJob(),
                info.getJobOtherDetail(),
                info.getIsStressedUnorganizedSchedule(),
                info.getIsForgetImportantThings(),
                info.getIsPreferReminder(),
                info.getIsImportantBreaks()
        );
    }
}