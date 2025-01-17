package com.official.memento.member.controller.dto;

import com.official.memento.global.exception.ErrorCode;
import com.official.memento.global.exception.MementoException;
import com.official.memento.member.domain.enums.JobType;

import java.time.LocalTime;

public record MemberPersonalInfoRequest(
        LocalTime wakeUpTime,
        LocalTime windDownTime,
        JobType job,
        String jobOtherDetail,
        Boolean isStressedUnorganizedSchedule,
        Boolean isForgetImportantThings,
        Boolean isPreferReminder,
        Boolean isImportantBreaks) {

    public MemberPersonalInfoRequest {
        validateNull(isStressedUnorganizedSchedule);
        validateNull(isForgetImportantThings);
        validateNull(isPreferReminder);
        validateNull(isImportantBreaks);

        if (job != JobType.OTHER && jobOtherDetail != null && !jobOtherDetail.isEmpty()) {
            throw new MementoException(ErrorCode.NULL_DATA_ERROR);
        }
        if (job == JobType.OTHER && (jobOtherDetail == null || jobOtherDetail.isEmpty())) {
            throw new MementoException(ErrorCode.NULL_DATA_ERROR);
        }
    }

    private void validateNull(final Object field) {
        //TODO: 머지하고 커스텀한 entity exception으로 교체하기
        if (field == null) {
            throw new MementoException(ErrorCode.NULL_DATA_ERROR);
        }
    }
}