package com.official.memento.orderinfo.infrastructure.persistence.projection;

import com.official.memento.orderinfo.domain.EventType;

import java.time.LocalDateTime;

public interface OrderInfoProjection {
    long getOrderInfoId();

    Long getScheduleId();

    Long getToDoId();

    LocalDateTime getStartDate();

    LocalDateTime getEndDate();

    Double getPriorityValue();

    int getOrderNum();

    EventType getEventType();

    LocalDateTime getCreatedAt();

}
