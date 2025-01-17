package com.official.memento.todo.domain;

import com.official.memento.global.entity.enums.RepeatOption;
import com.official.memento.todo.domain.enums.PriorityType;
import com.official.memento.todo.domain.enums.ToDoType;

import java.time.LocalDate;

public class ToDo {
    private Long id;
    private long memberId;
    private String groupId;
    private LocalDate date;
    private String description;
    private LocalDate deadline;
    private boolean isCompleted;
    private RepeatOption repeatOption;
    private LocalDate repeatExpiredDate;
    private Double priorityUrgency;
    private Double priorityImportance;
    private Double priorityValue;
    private String priorityType;
    private ToDoType type;
    private int order;

    private ToDo(
            final Long id,
            final long memberId,
            final String groupId,
            final LocalDate date,
            final String description,
            final LocalDate deadline,
            final boolean isCompleted,
            final RepeatOption repeatOption,
            final LocalDate repeatExpiredDate,
            final Double priorityUrgency,
            final Double priorityImportance,
            final Double priorityValue,
            final String priorityType,
            final ToDoType type,
            final int order
    ) {
        this.id = id;
        this.memberId = memberId;
        this.groupId = groupId;
        this.date = date;
        this.description = description;
        this.deadline = deadline;
        this.isCompleted = isCompleted;
        this.repeatOption = repeatOption;
        this.repeatExpiredDate = repeatExpiredDate;
        this.priorityUrgency = priorityUrgency;
        this.priorityImportance = priorityImportance;
        this.priorityValue = priorityValue;
        this.priorityType = priorityType;
        this.type = type;
        this.order = order;
    }

    private ToDo(
            final long memberId,
            final String groupId,
            final LocalDate date,
            final String description,
            final LocalDate deadline,
            final boolean isCompleted,
            final RepeatOption repeatOption,
            final LocalDate repeatExpiredDate,
            final Double priorityUrgency,
            final Double priorityImportance,
            final Double priorityValue,
            final String priorityType,
            final ToDoType type,
            final int order
    ) {
        this.memberId = memberId;
        this.groupId = groupId;
        this.date = date;
        this.description = description;
        this.deadline = deadline;
        this.isCompleted = isCompleted;
        this.repeatOption = repeatOption;
        this.repeatExpiredDate = repeatExpiredDate;
        this.priorityUrgency = priorityUrgency;
        this.priorityImportance = priorityImportance;
        this.priorityValue = priorityValue;
        this.priorityType = priorityType;
        this.type = type;
        this.order = order;
    }

    public static ToDo withId(
            final Long id,
            final long memberId,
            final String groupId,
            final LocalDate date,
            final String description,
            final LocalDate deadline,
            final boolean isCompleted,
            final RepeatOption repeatOption,
            final LocalDate repeatExpiredDate,
            final Double priorityUrgency,
            final Double priorityImportance,
            final Double priorityValue,
            final String priorityType,
            final ToDoType type,
            final int order
    ) {
        return new ToDo(
                id,
                memberId,
                groupId,
                date,
                description,
                deadline,
                isCompleted,
                repeatOption,
                repeatExpiredDate,
                priorityUrgency,
                priorityImportance,
                priorityValue,
                priorityType,
                type,
                order
        );
    }

    public static ToDo of(
            final long memberId,
            final String groupId,
            final LocalDate date,
            final String description,
            final LocalDate deadline,
            final boolean isCompleted,
            final RepeatOption repeatOption,
            final LocalDate repeatExpiredDate,
            final Double priorityUrgency,
            final Double priorityImportance,
            final Double priorityValue,
            final String priorityType,
            final ToDoType type,
            final int order
    ) {
        return new ToDo(
                memberId,
                groupId,
                date,
                description,
                deadline,
                isCompleted,
                repeatOption,
                repeatExpiredDate,
                priorityUrgency,
                priorityImportance,
                priorityValue,
                priorityType,
                type,
                order
        );
    }

    public Long getId() {
        return id;
    }

    public long getMemberId() {
        return memberId;
    }

    public String getGroupId() {
        return groupId;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public RepeatOption getRepeatOption() {
        return repeatOption;
    }

    public LocalDate getRepeatExpiredDate() {
        return repeatExpiredDate;
    }

    public Double getPriorityUrgency() {
        return priorityUrgency;
    }

    public Double getPriorityImportance() {
        return priorityImportance;
    }

    public Double getPriorityValue() {
        return priorityValue;
    }

    public String getPriorityType() {
        return priorityType;
    }

    public ToDoType getType() {
        return type;
    }

    public int getOrder() {
        return order;
    }
}
