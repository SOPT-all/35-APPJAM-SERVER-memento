package com.official.memento.todo.infrastructure.persistence;

import com.official.memento.global.entity.BaseTimeEntity;
import com.official.memento.global.entity.enums.RepeatOption;
import com.official.memento.todo.domain.ToDo;
import com.official.memento.todo.domain.enums.PriorityType;
import com.official.memento.todo.domain.enums.ToDoType;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "todo")
public class ToDoEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private long memberId;
    private String groupId;
    private LocalDate date;
    private String description;
    private LocalDate deadline;
    private boolean isCompleted;
    @Enumerated(EnumType.STRING)
    private RepeatOption repeatOption;
    private LocalDate repeatExpiredDate;
    private Double priorityUrgency;
    private Double priorityImportance;
    private Double priorityValue;
    private String priorityType;
    @Enumerated(EnumType.STRING)
    private ToDoType type;

    protected ToDoEntity(){
    }

    private ToDoEntity(
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
            final ToDoType type
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
    }

    public static ToDoEntity of(final ToDo toDo) {
        return new ToDoEntity(
                toDo.getMemberId(),
                toDo.getGroupId(),
                toDo.getDate(),
                toDo.getDescription(),
                toDo.getDeadline(),
                toDo.isCompleted(),
                toDo.getRepeatOption(),
                toDo.getRepeatExpiredDate(),
                toDo.getPriorityUrgency(),
                toDo.getPriorityImportance(),
                toDo.getPriorityValue(),
                toDo.getPriorityType(),
                toDo.getType()
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
}
