package com.official.memento.member.service;

import com.official.memento.member.controller.dto.MemberResponse;
import com.official.memento.member.domain.MemberPersonalInfo;
import com.official.memento.member.domain.port.MemberPersonalInfoRepository;
import com.official.memento.member.service.command.MemberUpdateCommand;
import com.official.memento.member.service.usecase.MemberUpdateUseCase;
import org.springframework.stereotype.Service;

@Service
public class MemberService implements MemberUpdateUseCase {
    private final MemberPersonalInfoRepository memberPersonalInfoRepository;

    public MemberService(MemberPersonalInfoRepository memberPersonalInfoRepository) {
        this.memberPersonalInfoRepository = memberPersonalInfoRepository;
    }

    @Override
    public MemberResponse updatePersonalInfo(MemberUpdateCommand command) {
        MemberPersonalInfo existingInfo = memberPersonalInfoRepository.findByMemberId(command.memberId());

        MemberPersonalInfo updatedInfo = MemberPersonalInfo.of(
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

        MemberPersonalInfo savedInfo = memberPersonalInfoRepository.save(updatedInfo);

        return new MemberResponse(
                savedInfo.getMemberId(),
                savedInfo.getWakeUpTime(),
                savedInfo.getWindDownTime(),
                savedInfo.getJob(),
                savedInfo.getJobOtherDetail(),
                savedInfo.getIsStressedUnorganizedSchedule(),
                savedInfo.getIsForgetImportantThings(),
                savedInfo.getIsPreferReminder(),
                savedInfo.getIsImportantBreaks()
        );
    }
}