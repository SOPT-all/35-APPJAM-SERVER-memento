package com.official.memento.orderinfo.domain;

import java.time.LocalDateTime;

public class OrderWithScheduleOrToDo {
    private long orderInfoId;
    private Long scheduleId;
    private Long toDoId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Double priorityValue;
    private int order;
    private EventType type;
    private LocalDateTime createdAt;

    private OrderWithScheduleOrToDo(
            final Long orderInfoId,
            final Long scheduleId,
            final Long toDoId,
            final LocalDateTime startDate,
            final LocalDateTime endDate,
            final Double priorityValue,
            final int order,
            final EventType type,
            final LocalDateTime createdAt
    ) {
        this.orderInfoId = orderInfoId;
        this.scheduleId = scheduleId;
        this.toDoId = toDoId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.priorityValue = priorityValue;
        this.order = order;
        this.type = type;
        this.createdAt = createdAt;
    }

    public static OrderWithScheduleOrToDo of(
            final Long orderInfoId,
            final Long scheduleId,
            final Long toDoId,
            final LocalDateTime startDate,
            final LocalDateTime endDate,
            final Double priorityValue,
            final int order,
            final EventType eventType,
            final LocalDateTime createdAt

    ) {
        return new OrderWithScheduleOrToDo(
                orderInfoId,
                scheduleId,
                toDoId,
                startDate,
                endDate,
                priorityValue,
                order,
                eventType,
                createdAt
        );
    }

    public Long getOrderInfoId() {
        return orderInfoId;
    }

    public Long getScheduleId() {
        return scheduleId;
    }

    public Long getToDoId() {
        return toDoId;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public Double getPriorityValue() {
        return priorityValue;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public int getOrder() {
        return order;
    }

    public void shiftBack() {
        this.order++;
    }

    public EventType getType() {
        return type;
    }

    public void shiftForward() {
        this.order--;
    }
}
