package com.official.memento.schedule.service.command;

import com.official.memento.global.exception.NullPointException;

import java.time.LocalDateTime;

import static com.official.memento.global.exception.ErrorCode.NULL_DATA_ERROR;

public record ScheduleCreateCommand(
        long memberId,
        String description,
        LocalDateTime startDate,
        LocalDateTime endDate,
        boolean isAllDay,
        Long tagId
) {
    public static ScheduleCreateCommand of(
            final long memberId,
            final String description,
            final LocalDateTime startDate,
            final LocalDateTime endDate,
            final boolean isAllDay,
            final Long tagId
    ) {
        checkNullData(description, startDate, endDate, tagId);
        return new ScheduleCreateCommand(
                memberId,
                description,
                startDate,
                endDate,
                isAllDay,
                tagId
        );
    }

    private static void checkNullData(
            final String description,
            final LocalDateTime startDate,
            final LocalDateTime endDate,
            final Long tagId
    ) {
        if (description == null | startDate == null || endDate == null || tagId == null) {
            throw new NullPointException(NULL_DATA_ERROR);
        }
    }
}
