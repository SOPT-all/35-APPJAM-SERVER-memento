package com.official.memento.schedule.service.command;

import com.official.memento.global.entity.enums.RepeatOption;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record ScheduleUpdateCommand(
        long memberId,
        long scheduleId,
        String description,
        LocalDateTime startDate,
        LocalDateTime endDate,
        boolean isAllDay,
        Long tagId
) {
    public static ScheduleUpdateCommand of(
            final long memberId,
            final long scheduleId,
            final String description,
            final LocalDateTime startDate,
            final LocalDateTime endDate,
            final boolean isAllDay,
            final Long tagId
    ) {
        return new ScheduleUpdateCommand(
                memberId,
                scheduleId,
                description,
                startDate,
                endDate,
                isAllDay,
                tagId
        );
    }
}
