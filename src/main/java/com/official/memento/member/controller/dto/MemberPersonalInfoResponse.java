package com.official.memento.member.controller.dto;

import com.official.memento.global.exception.ErrorCode;
import com.official.memento.global.exception.MementoException;
import com.official.memento.member.domain.enums.JobType;

import java.time.LocalTime;

public record MemberPersonalInfoResponse(
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
    public static MemberPersonalInfoResponse of(
            final Long memberId,
            final LocalTime wakeUpTime,
            final LocalTime windDownTime,
            final JobType job,
            final String jobOtherDetail,
            final Boolean isStressedUnorganizedSchedule,
            final Boolean isForgetImportantThings,
            final Boolean isPreferReminder,
            final Boolean isImportantBreaks)
    {
        validateNull(isStressedUnorganizedSchedule);
        validateNull(isForgetImportantThings);
        validateNull(isPreferReminder);
        validateNull(isImportantBreaks);
        return new MemberPersonalInfoResponse(
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

    private static void validateNull(final Object field) {
        if (field == null) {
            throw new MementoException(ErrorCode.NULL_DATA_ERROR);
        }
    }
}