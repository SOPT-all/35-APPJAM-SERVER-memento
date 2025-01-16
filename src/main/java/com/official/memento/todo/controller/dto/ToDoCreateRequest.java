package com.official.memento.todo.controller.dto;

import com.official.memento.schedule.domain.enums.RepeatOption;
import com.official.memento.tag.domain.enums.TagColor;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Schema(name = "ToDo 생성 요청")
public record ToDoCreateRequest(
        @Schema(description = "ToDo 날짜 (Today or 사용자 지정")
        LocalDate date,
        @Schema(description = "ToDo 내용", maxLength = 30)
        String description,
        @Schema(description = "마감 기한 (반복 여부에 따라 동작)")
        LocalDate deadline,
        @Schema(description = "반복 여부")
        boolean isRepeated,
        @Schema(description = "반복 기준 (반복 여부에 따라 동작)")
        RepeatOption repeatOption,
        @Schema(description = "반복 종료 날짜")
        LocalDate repeatExpiredDate,
        @Schema(description = "태그 ID")
        Long tagId,
        // x값, y값 따로 받아야 할 듯...?
        @Schema(description = "우선순위 (아이젠하워 매트릭스 기준 1~4분면 중앙 위치 (x, y)값)", example = "(?,?)")
        String priorityType
) {
    public ToDoCreateRequest(
            final LocalDate date,
            final String description,
            final LocalDate deadline,
            final boolean isRepeated,
            final RepeatOption repeatOption,
            final LocalDate repeatExpiredDate,
            final Long tagId,
            final String priorityType

    ) {
        // 설명 30자 제한
        // 에러는 만들어서 던지는걸로 수정 필요(민규가 만들어 둠)
        if (description != null && description.length() > 30) {
            throw new IllegalArgumentException("30자 이하로만 작성이 가능합니다.");
        }

        checkNullData(date, description, repeatOption, tagId);

        this.date = date;
        this.description = description;
        this.deadline = deadline;
        this.isRepeated = isRepeated;
        this.repeatOption = repeatOption;
        this.repeatExpiredDate = repeatExpiredDate;
        this.tagId = tagId;
        this.priorityType = priorityType;
    }

    private static void checkNullData(LocalDate date, String description, RepeatOption repeatOption, Long tagId) {
        if (description == null || repeatOption == null || tagId == null) {
            throw new IllegalArgumentException("올바른 형식이 아닙니다.");
        }
    }
}
