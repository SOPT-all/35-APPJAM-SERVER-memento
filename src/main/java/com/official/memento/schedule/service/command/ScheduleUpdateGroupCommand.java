package com.official.memento.schedule.service.command;

import com.official.memento.global.entity.enums.RepeatOption;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record ScheduleUpdateGroupCommand(
        long memberId,
        long scheduleId,
        String description,
        LocalDateTime startDate,
        LocalDateTime endDate,

        RepeatOption repeatOption,
        LocalDate repeatExpiredDate,
        boolean isAllDay,
        Long tagId,
        String scheduleGroupId
) {
    public static ScheduleUpdateGroupCommand of(
            final long memberId,
            final long scheduleId,
            final String description,
            final LocalDateTime startDate,
            final LocalDateTime endDate,
            final RepeatOption repeatOption,
            final LocalDate repeatExpiredDate,
            final boolean isAllDay,
            final Long tagId,
            final String scheduleGroupId
    ) {
        return new ScheduleUpdateGroupCommand(
                memberId,
                scheduleId,
                description,
                startDate,
                endDate,
                repeatOption,
                repeatExpiredDate,
                isAllDay,
                tagId,
                scheduleGroupId
        );
    }
}
