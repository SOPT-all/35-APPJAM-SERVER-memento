package com.official.memento.schedule.infrastructure.persistence;

import com.official.memento.global.entity.BaseTimeEntity;
import com.official.memento.schedule.domain.Schedule;
import com.official.memento.global.entity.enums.RepeatOption;
import com.official.memento.schedule.domain.enums.ScheduleType;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "schedule")
public class ScheduleEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private long memberId;
    private String description;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private boolean isAllDay;
    @Enumerated(EnumType.STRING)
    private RepeatOption repeatOption;
    private LocalDate repeatExpiredDate;
    @Enumerated(EnumType.STRING)
    private ScheduleType type;

    protected ScheduleEntity() {
    }

    private ScheduleEntity(
            final long memberId,
            final String description,
            final LocalDateTime startDate,
            final LocalDateTime endDate,
            final boolean isAllDay,
            final RepeatOption repeatOption,
            final LocalDate repeatExpiredDate,
            final ScheduleType type
    ) {
        this.memberId = memberId;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.isAllDay = isAllDay;
        this.repeatOption = repeatOption;
        this.repeatExpiredDate = repeatExpiredDate;
        this.type = type;
    }

    public static ScheduleEntity of(final Schedule schedule) {
        return new ScheduleEntity(
                schedule.getMemberId(),
                schedule.getDescription(),
                schedule.getStartDate(),
                schedule.getEndDate(),
                schedule.isAllDay(),
                schedule.getRepeatOption(),
                schedule.getRepeatExpiredDate(),
                schedule.getType()
        );
    }

    public Long getId() {
        return id;
    }

    public long getMemberId() {
        return memberId;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public boolean isAllDay() {
        return isAllDay;
    }

    public RepeatOption getRepeatOption() {
        return repeatOption;
    }

    public LocalDate getRepeatExpiredDate() {
        return repeatExpiredDate;
    }

    public ScheduleType getType() {
        return type;
    }
}
