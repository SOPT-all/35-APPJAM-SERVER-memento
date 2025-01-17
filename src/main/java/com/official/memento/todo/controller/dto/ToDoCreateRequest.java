package com.official.memento.todo.controller.dto;

import com.official.memento.global.entity.enums.RepeatOption;
import com.official.memento.global.exception.NullPointException;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;

import static com.official.memento.global.exception.ErrorCode.NULL_DATA_ERROR;

@Schema(name = "ToDo 생성 요청")
public record ToDoCreateRequest(
        @Schema(description = "ToDo 날짜 (Today or 사용자 지정)", example = "2025-01-20")
        LocalDate date,
        @Schema(description = "ToDo 내용", maxLength = 30, example = "팀 프로젝트 준비")
        String description,
        @Schema(description = "마감 기한", example = "2025-01-25")
        LocalDate deadline,
        @Schema(description = "반복 옵션")
        RepeatOption repeatOption,
        @Schema(description = "반복 종료 날짜")
        LocalDate repeatExpiredDate,
        @Schema(description = "태그 ID", example = "12345")
        Long tagId,
        @Schema(description = "긴급도 우선순위 (0~1)", example = "0.5")
        double priorityUrgency,
        @Schema(description = "중요도 우선순위 (0~1)", example = "0.5")
        double priorityImportance
) {
    public ToDoCreateRequest(
            final LocalDate date,
            final String description,
            final LocalDate deadline,
            final RepeatOption repeatOption,
            final LocalDate repeatExpiredDate,
            final Long tagId,
            final double priorityUrgency,
            final double priorityImportance
    ) {
        checkNullData(date, description, repeatOption);

        if (description.length() > 30) {
            throw new IllegalArgumentException("30자 이하로만 작성이 가능합니다.");
        }

        if (priorityUrgency < 0 || priorityUrgency > 1) {
            throw new IllegalArgumentException("priorityUrgency 값은 0과 1 사이여야 합니다.");
        }

        if (priorityImportance < 0 || priorityImportance > 1) {
            throw new IllegalArgumentException("priorityImportance 값은 0과 1 사이여야 합니다.");
        }

        this.date = date;
        this.description = description;
        this.deadline = deadline;
        this.repeatOption = repeatOption;
        this.repeatExpiredDate = repeatExpiredDate;
        this.tagId = tagId;
        this.priorityUrgency = priorityUrgency;
        this.priorityImportance = priorityImportance;
    }

    private static void checkNullData(
            final LocalDate date,
            final String description,
            final RepeatOption repeatOption
    ) {
        if (date == null || description == null || repeatOption == null) {
            throw new NullPointException(NULL_DATA_ERROR);
        }
    }
}
