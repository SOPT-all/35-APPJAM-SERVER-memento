package com.official.memento.schedule.conntroller.dto.request;

import com.official.memento.global.exception.NullPointException;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

import static com.official.memento.global.exception.ErrorCode.NULL_DATA_ERROR;

public record ScheduleUpdateRequest(
        @Schema(description = "일정 내용")
        String description,
        @Schema(description = "일정 시작 날짜")
        LocalDateTime startDate,
        @Schema(description = "일정 종료 날짜")
        LocalDateTime endDate,
        @Schema(description = "AllDay 여부")
        boolean isAllDay,
        @Schema(description = "태그 아이디")
        Long tagId

) {
    public ScheduleUpdateRequest of(
            final String description,
            final LocalDateTime startDate,
            final LocalDateTime endDate,
            final boolean isAllDay,
            final Long tagId
    ) {
        checkNullData(description, startDate, endDate);
        return new ScheduleUpdateRequest(description, startDate, endDate, isAllDay, tagId);
    }

    private static void checkNullData(
            final String description,
            final LocalDateTime startDate,
            final LocalDateTime endDate
    ) {
        if (description == null | startDate == null || endDate == null) {
            throw new NullPointException(NULL_DATA_ERROR);
        }
    }
}
