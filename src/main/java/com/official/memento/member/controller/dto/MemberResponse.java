package com.official.memento.member.controller.dto;

import com.official.memento.member.domain.enums.JobType;

import java.time.LocalTime;

public record MemberResponse(
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
    public static MemberResponse of(
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
        return new MemberResponse(
                memberId,
                wakeUpTime,
                windDownTime,
                job,
                jobOtherDetail,
                isStressedUnorganizedSchedule,
                isForgetImportantThings,
                isPreferReminder,
                isImportantBreaks
        );
    }
}