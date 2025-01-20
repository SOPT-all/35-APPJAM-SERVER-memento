package com.official.memento.orderinfo.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class OrderInfo {
    private Long id;
    private Long scheduleId;
    private Long toDoId;
    private int orderNum;
    private LocalDate date;
    private EventType eventType;
    private LocalDateTime createdAt;


    private OrderInfo(
            final Long id,
            final Long scheduleId,
            final Long toDoId,
            final int orderNum,
            final LocalDate date,
            final EventType eventType,
            final LocalDateTime createdAt
    ) {
        this.id = id;
        this.scheduleId = scheduleId;
        this.toDoId = toDoId;
        this.orderNum = orderNum;
        this.date = date;
        this.eventType = eventType;
        this.createdAt = createdAt;
    }

    private OrderInfo(
            final Long scheduleId,
            final Long toDoId,
            final int orderNum,
            final LocalDate date,
            final EventType eventType,
            final LocalDateTime createdAt
    ) {
        this.scheduleId = scheduleId;
        this.toDoId = toDoId;
        this.orderNum = orderNum;
        this.date = date;
        this.eventType = eventType;
        this.createdAt = createdAt;
    }

    public static OrderInfo of(
            final Long scheduleId,
            final Long toDoId,
            final int order,
            final LocalDate date,
            final EventType eventType,
            final LocalDateTime createdAt
    ) {
        return new OrderInfo(scheduleId, toDoId, order, date, eventType, createdAt);
    }

    public static OrderInfo withId(
            final Long id,
            final Long scheduleId,
            final Long toDoId,
            final int order,
            final LocalDate date,
            final EventType eventType,
            final LocalDateTime createdAt
    ) {
        return new OrderInfo(id, scheduleId, toDoId, order, date, eventType, createdAt);
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public Long getScheduleId() {
        return scheduleId;
    }

    public Long getToDoId() {
        return toDoId;
    }

    public LocalDate getDate() {
        return date;
    }

    public EventType getEventType() {
        return eventType;
    }

    public int getOrderNum() {
        return orderNum;
    }
}
