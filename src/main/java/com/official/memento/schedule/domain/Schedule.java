package com.official.memento.schedule.domain;

import com.official.memento.global.entity.enums.RepeatOption;
import com.official.memento.schedule.domain.enums.ScheduleType;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Schedule {
    private Long id;
    private long memberId;
    private String description;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private boolean isAllDay;
    private RepeatOption repeatOption;
    private LocalDate repeatExpiredDate;
    private ScheduleType type;
    private String scheduleGroupId;

    private Schedule(
            final Long id,
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
    }

    private Schedule(
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

    public static Schedule withId(
            final Long id,
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
        return new Schedule(
                id,
                memberId,
                description,
                startDate,
                endDate,
                isAllDay,
                repeatOption,
                repeatExpiredDate,
                type,
                scheduleGroupId
        );
    }

    public static Schedule of(
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
        return new Schedule(
                memberId,
                description,
                startDate,
                endDate,
                isAllDay,
                repeatOption,
                repeatExpiredDate,
                type,
                scheduleGroupId
        );
    }

    public Schedule update(
            final String description,
            final LocalDateTime startDate,
            final LocalDateTime endDate,
            final boolean isAllDay
    ) {
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.isAllDay = isAllDay;
        return this;
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
