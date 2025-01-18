package com.official.memento.member.service;

import com.official.memento.member.domain.MemberPersonalInfo;
import com.official.memento.member.domain.port.MemberPersonalInfoRepository;
import com.official.memento.member.service.command.MemberPersonalInfoCommand;
import com.official.memento.member.service.usecase.MemberPersonalInfoUpdateUseCase;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MemberPersonalInfoService implements MemberPersonalInfoUpdateUseCase {

    private final MemberPersonalInfoRepository memberPersonalInfoRepository;

    public MemberPersonalInfoService(final MemberPersonalInfoRepository memberPersonalInfoRepository) {
        this.memberPersonalInfoRepository = memberPersonalInfoRepository;
    }

    @Override
    @Transactional
    public MemberPersonalInfo update(final MemberPersonalInfoCommand command) {
        return memberPersonalInfoRepository.update(
                MemberPersonalInfo.of(
                        command.memberId(),
                        command.wakeUpTime(),
                        command.windDownTime(),
                        command.job(),
                        command.jobOtherDetail(),
                        command.isStressedUnorganizedSchedule(),
                        command.isForgetImportantThings(),
                        command.isPreferReminder(),
                        command.isImportantBreaks()
                ));
    }
}