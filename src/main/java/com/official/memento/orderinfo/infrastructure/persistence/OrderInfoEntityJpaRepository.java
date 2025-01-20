package com.official.memento.orderinfo.infrastructure.persistence;

import com.official.memento.orderinfo.infrastructure.persistence.projection.OrderInfoProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface OrderInfoEntityJpaRepository extends JpaRepository<OrderInfoEntity, Long> {
    @Query("""
            SELECT o.id AS orderInfoId,
                   o.toDoId AS toDoId,
                   o.scheduleId AS scheduleId,
                   s.startDate AS startDate,
                   s.endDate AS endDate,
                   t.priorityValue AS priorityValue,
                   o.orderNum AS orderNum,
                   o.eventType AS eventType,
                   o.createdAt As createdAt
            FROM OrderInfoEntity o
            LEFT JOIN ToDoEntity t ON o.toDoId = t.id AND o.eventType = 'ToDo'
            LEFT JOIN ScheduleEntity s ON o.scheduleId = s.id AND o.eventType = 'Schedule'
            WHERE DATE(o.date) = :startDate
            ORDER BY o.orderNum ASC, o.createdAt ASC
            """)
    List<OrderInfoProjection> findOrderInfoWithDetails(final LocalDate startDate);
}