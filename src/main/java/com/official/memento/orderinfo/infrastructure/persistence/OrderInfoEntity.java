package com.official.memento.orderinfo.infrastructure.persistence;

import com.official.memento.orderinfo.domain.EventType;
import com.official.memento.orderinfo.domain.OrderInfo;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "order_info")
public class OrderInfoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long scheduleId;
    private Long toDoId;
    private int orderNum;
    private LocalDate date;
    @Enumerated(EnumType.STRING)
    private EventType eventType;
    private LocalDateTime createdAt;

    private OrderInfoEntity(
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

    private OrderInfoEntity(
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

    protected OrderInfoEntity() {

    }

    public static OrderInfoEntity of(final OrderInfo orderInfo) {
        return new OrderInfoEntity(
                orderInfo.getScheduleId(),
                orderInfo.getToDoId(),
                orderInfo.getOrderNum(),
                orderInfo.getDate(),
                orderInfo.getEventType(),
                orderInfo.getCreatedAt()
        );
    }

    public static OrderInfoEntity withId(
            final OrderInfo orderInfo
    ) {
        return new OrderInfoEntity(
                orderInfo.getId(),
                orderInfo.getScheduleId(),
                orderInfo.getToDoId(),
                orderInfo.getOrderNum(),
                orderInfo.getDate(),
                orderInfo.getEventType(),
                orderInfo.getCreatedAt()
        );
    }

    public Long getId() {
        return id;
    }

    public Long getScheduleId() {
        return scheduleId;
    }

    public LocalDate getDate() {
        return date;
    }

    public Long getToDoId() {
        return toDoId;
    }

    public int getOrderNum() {
        return orderNum;
    }

    public EventType getEventType() {
        return eventType;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
