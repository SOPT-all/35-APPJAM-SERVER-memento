package com.official.memento.member.service;

import com.official.memento.global.exception.EntityNotFoundException;
import com.official.memento.global.exception.ErrorCode;
import com.official.memento.member.controller.dto.MemberPersonalInfoResponse;
import com.official.memento.member.domain.MemberPersonalInfo;
import com.official.memento.member.domain.port.MemberPersonalInfoRepository;
import com.official.memento.member.service.command.MemberPersonalInfoCommand;
import com.official.memento.member.service.usecase.MemberPersonalInfoUseCase;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MemberService implements MemberPersonalInfoUseCase {

    private final MemberPersonalInfoRepository memberPersonalInfoRepository;

    public MemberService(final MemberPersonalInfoRepository memberPersonalInfoRepository) {
        this.memberPersonalInfoRepository = memberPersonalInfoRepository;
    }

    @Override
    @Transactional
    public MemberPersonalInfo updatePersonalInfo(final MemberPersonalInfoCommand command) {
        final MemberPersonalInfo existingInfo = memberPersonalInfoRepository
                .findByMemberId(command.memberId())
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.NOT_FOUND_ENTITY));
        existingInfo.update(
                command.wakeUpTime(),
                command.windDownTime(),
                command.job(),
                command.jobOtherDetail(),
                command.isStressedUnorganizedSchedule(),
                command.isForgetImportantThings(),
                command.isPreferReminder(),
                command.isImportantBreaks());
        return memberPersonalInfoRepository.update(existingInfo);
    }
}