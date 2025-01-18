package com.official.memento.member.service;

import com.official.memento.global.exception.EntityNotFoundException;
import com.official.memento.global.exception.ErrorCode;
import com.official.memento.member.controller.dto.MemberUptimeResponse;
import com.official.memento.member.domain.MemberPersonalInfo;
import com.official.memento.member.domain.port.MemberPersonalInfoRepository;
import com.official.memento.member.service.command.MemberPersonalInfoCommand;
import com.official.memento.member.service.usecase.MemberPersonalInfoUseCase;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MemberPersonalInfoService implements MemberPersonalInfoUseCase {

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

    @Override
    @Transactional(readOnly = true)
    public MemberUptimeResponse retrieveUptime(final Long memberId) {
        return memberPersonalInfoRepository.findByMemberId(memberId)
                .map(info -> MemberUptimeResponse.of(
                        info.getWakeUpTime().toString(),
                        info.getWindDownTime().toString()
                ))
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.NOT_FOUND_ENTITY));
    }
}