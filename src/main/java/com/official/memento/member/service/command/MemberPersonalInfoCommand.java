package com.official.memento.member.service.command;

import com.official.memento.member.domain.enums.JobType;

import java.time.LocalTime;

public record MemberPersonalInfoCommand(
        Long memberId,
        LocalTime wakeUpTime,
        LocalTime windDownTime,
        JobType job,
        String jobOtherDetail,
        Boolean isStressedUnorganizedSchedule,
        Boolean isForgetImportantThings,
        Boolean isPreferReminder,
        Boolean isImportantBreaks
) {
}