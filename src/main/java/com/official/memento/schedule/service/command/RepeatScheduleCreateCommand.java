package com.official.memento.schedule.service.command;

import com.official.memento.global.entity.enums.RepeatOption;
import com.official.memento.global.exception.NullPointException;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static com.official.memento.global.exception.ErrorCode.NULL_DATA_ERROR;

public record RepeatScheduleCreateCommand(
        long memberId,
        String description,
        LocalDateTime startDate,
        LocalDateTime endDate,
        boolean isAllDay,
        RepeatOption repeatOption,
        LocalDate repeatExpiredDate,
        Long tagId
) {
    public static RepeatScheduleCreateCommand of(
            final long memberId,
            final String description,
            final LocalDateTime startDate,
            final LocalDateTime endDate,
            final boolean isAllDay,
            final RepeatOption repeatOption,
            final LocalDate repeatExpiredDate,
            final Long tagId
    ) {
        checkNullData(description, startDate, endDate, repeatOption, tagId);
        return new RepeatScheduleCreateCommand(
                memberId,
                description,
                startDate,
                endDate,
                isAllDay,
                repeatOption,
                repeatExpiredDate,
                tagId
        );
    }

    private static void checkNullData(
            final String description,
            final LocalDateTime startDate,
            final LocalDateTime endDate,
            final RepeatOption repeatOption, final Long tagId
    ) {
        if (description == null | startDate == null || endDate == null || repeatOption == null || tagId == null) {
            throw new NullPointException(NULL_DATA_ERROR);
        }
    }
}