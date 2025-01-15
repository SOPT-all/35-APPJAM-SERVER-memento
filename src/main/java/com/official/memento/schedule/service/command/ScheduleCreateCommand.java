package com.official.memento.schedule.service.command;

import com.official.memento.global.exception.NullPointException;
import com.official.memento.schedule.domain.enums.RepeatOption;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static com.official.memento.global.exception.ErrorCode.NULL_DATA_ERROR;

public record ScheduleCreateCommand(
        long memberId,
        String description,
        LocalDateTime startDate,
        LocalDateTime endDate,
        boolean isAllDay,
        RepeatOption repeatOption,
        LocalDate repeatExpiredDate,
        Long tagId
) {
    public static ScheduleCreateCommand of(
            final long memberId,
            final String description,
            final LocalDateTime startDate,
            final LocalDateTime endDate,
            final boolean isAllDay,
            final RepeatOption repeatOption,
            final LocalDate repeatExpiredDate,
            final Long tagId
    ){
        checkNullData(description, startDate, endDate, repeatOption, tagId);
        return new ScheduleCreateCommand(
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

    private static void checkNullData(String description, LocalDateTime startDate, LocalDateTime endDate, RepeatOption repeatOption, Long tagId) {
        if(description ==null| startDate ==null || endDate ==null|| repeatOption ==null || tagId ==null){
            throw new NullPointException(NULL_DATA_ERROR);
        }
    }
}
