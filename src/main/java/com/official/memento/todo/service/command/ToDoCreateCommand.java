package com.official.memento.todo.service.command;

import com.official.memento.global.entity.enums.RepeatOption;

import java.time.LocalDate;

public record ToDoCreateCommand(
        long memberId,
        LocalDate date,
        String description,
        LocalDate deadline,
        RepeatOption repeatOption,
        LocalDate repeatExpiredDate,
        Long tagId,
        Double priorityUrgency,
        Double priorityImportance
) {
    public static ToDoCreateCommand of(
            final long memberId,
            final LocalDate date,
            final String description,
            final LocalDate deadline,
            final RepeatOption repeatOption,
            final LocalDate repeatExpiredDate,
            final Long tagId,
            final Double priorityUrgency,
            final Double priorityImportance
    ) {
        return new ToDoCreateCommand(
                memberId,
                date,
                description,
                deadline,
                repeatOption,
                repeatExpiredDate,
                tagId,
                priorityUrgency,
                priorityImportance
        );
    }
}
