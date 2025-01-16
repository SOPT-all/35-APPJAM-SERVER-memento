package com.official.memento.schedule.conntroller.dto.request;

import com.official.memento.global.entity.enums.RepeatOption;
import com.official.memento.global.exception.NullPointException;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static com.official.memento.global.exception.ErrorCode.NULL_DATA_ERROR;

@Schema(name = "반복 일정 생성 요청")
public record RepeatScheduleCreateRequest(
        @Schema(description = "일정 내용")
        String description,
        @Schema(description = "일정 시작 날짜")
        LocalDateTime startDate,
        @Schema(description = "일정 종료 날짜")
        LocalDateTime endDate,
        @Schema(description = "AllDay 여부")
        boolean isAllDay,
        @Schema(description = "반복 옵션")
        RepeatOption repeatOption,
        @Schema(description = "반복 종료 날짜")
        LocalDate repeatExpiredDate,
        @Schema(description = "태그 아이디")
        Long tagId
) {
    public RepeatScheduleCreateRequest(
            final String description,
            final LocalDateTime startDate,
            final LocalDateTime endDate,
            final boolean isAllDay,
            final RepeatOption repeatOption,
            final LocalDate repeatExpiredDate,
            final Long tagId
    ) {
        checkNullData(description, startDate, endDate, repeatOption);
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.isAllDay = isAllDay;
        this.repeatOption = repeatOption;
        this.repeatExpiredDate = repeatExpiredDate;
        this.tagId = tagId;
    }

    private static void checkNullData(
            final String description,
            final LocalDateTime startDate,
            final LocalDateTime endDate,
            final RepeatOption repeatOption
    ) {
        if (description == null | startDate == null || endDate == null || repeatOption == null) {
            throw new NullPointException(NULL_DATA_ERROR);
        }
    }
}

