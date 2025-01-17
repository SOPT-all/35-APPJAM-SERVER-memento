package com.official.memento.schedule.infrastructure.persistence;

import com.official.memento.global.entity.BaseTimeEntity;
import com.official.memento.global.entity.enums.RepeatOption;
import com.official.memento.schedule.domain.Schedule;
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
    private String scheduleGroupId;

    protected ScheduleEntity() {
    }

    private ScheduleEntity(
            final long id,
            final long memberId,
            final String description,
            final LocalDateTime startDate,
            final LocalDateTime endDate,
            final boolean isAllDay,
            final RepeatOption repeatOption,
            final LocalDate repeatExpiredDate,
            final ScheduleType type,
            final String scheduleGroupId,
            final LocalDateTime createdAt,
            final LocalDateTime updatedAt
    ) {
        this.id = id;
        this.memberId = memberId;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.isAllDay = isAllDay;
        this.repeatOption = repeatOption;
        this.repeatExpiredDate = repeatExpiredDate;
        this.type = type;
        this.scheduleGroupId = scheduleGroupId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    private ScheduleEntity(
            final long memberId,
            final String description,
            final LocalDateTime startDate,
            final LocalDateTime endDate,
            final boolean isAllDay,
            final RepeatOption repeatOption,
            final LocalDate repeatExpiredDate,
            final ScheduleType type,
            final String scheduleGroupId
    ) {
        this.memberId = memberId;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.isAllDay = isAllDay;
        this.repeatOption = repeatOption;
        this.repeatExpiredDate = repeatExpiredDate;
        this.type = type;
        this.scheduleGroupId = scheduleGroupId;
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
                schedule.getType(),
                schedule.getScheduleGroupId()
        );
    }

    public static ScheduleEntity withId(final Schedule schedule) {
        return new ScheduleEntity(
                schedule.getId(),
                schedule.getMemberId(),
                schedule.getDescription(),
                schedule.getStartDate(),
                schedule.getEndDate(),
                schedule.isAllDay(),
                schedule.getRepeatOption(),
                schedule.getRepeatExpiredDate(),
                schedule.getType(),
                schedule.getScheduleGroupId(),
                schedule.getCreatedAt(),
                schedule.getUpdatedAt()
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

    public String getScheduleGroupId() {
        return scheduleGroupId;
    }
}
