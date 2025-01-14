package com.official.memento.member.controller.dto;

import com.official.memento.member.domain.enums.JobType;

import java.time.LocalTime;

public record MemberRequest(
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