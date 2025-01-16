package com.official.memento.todo.service.command;

import com.official.memento.schedule.domain.enums.RepeatOption;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;

import static com.official.memento.todo.controller.dto.ToDoCreateRequest.checkNullData;

public record ToDoCreateCommand(
        long memberId,
        LocalDate date,
        String description,
        LocalDate deadline,
        boolean isRepeated,
        RepeatOption repeatOption,
        LocalDate repeatExpiredDate,
        Long tagId,
        String priorityType
) {
    public static ToDoCreateCommand of(
            final long memberId,
            final LocalDate date,
            final String description,
            final LocalDate deadline,
            final boolean isRepeated,
            final RepeatOption repeatOption,
            final LocalDate repeatExpiredDate,
            final Long tagId,
            final String priorityType
    ){
        checkNullData(date, description, repeatOption, tagId);
        return new ToDoCreateCommand(memberId, date, description, deadline, isRepeated, repeatOption, repeatExpiredDate, tagId, priorityType);
    }

    private static void checkNullData(LocalDate date, String description, RepeatOption repeatOption, Long tagId) {
        if (description == null || repeatOption == null || tagId == null) {
            throw new IllegalArgumentException("올바른 형식이 아닙니다.");
        }
    }
}
