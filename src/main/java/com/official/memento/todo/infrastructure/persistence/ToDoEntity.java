package com.official.memento.todo.infrastructure.persistence;

import com.official.memento.schedule.domain.enums.RepeatOption;
import com.official.memento.todo.domain.enums.PriorityType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Table(name = "todo")
public class ToDoEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long memberId;
    private String description;
    private LocalDateTime deadline;
    private RepeatOption repeatOption;
    private LocalDateTime repeatExpiredDate;
    private PriorityType priorityType;
    private boolean isCompleted = false;
    private boolean isRepeated = false;

}
