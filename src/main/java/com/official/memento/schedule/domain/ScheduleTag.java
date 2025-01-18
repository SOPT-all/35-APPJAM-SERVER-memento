package com.official.memento.schedule.domain;

import com.official.memento.global.entity.BaseTimeEntity;

import java.time.LocalDateTime;

public class ScheduleTag extends BaseTimeEntity {
    private Long id;
    private long tagId;
    private long scheduleId;

    public ScheduleTag(
            final Long id,
            final long tagId,
            final long scheduleId,
            final LocalDateTime createAt,
            final LocalDateTime updatedAt
    ) {
        this.id = id;
        this.tagId = tagId;
        this.scheduleId = scheduleId;
        this.createdAt = createAt;
        this.updatedAt = updatedAt;
    }

    public ScheduleTag(final long tagId, final long scheduleId) {
        this.tagId = tagId;
        this.scheduleId = scheduleId;
    }

    public static ScheduleTag withId(
            final Long id,
            final long tagId,
            final long scheduleId,
            final LocalDateTime createdAt,
            final LocalDateTime updateAt
    ) {
        return new ScheduleTag(id, tagId, scheduleId, createdAt, updateAt);
    }

    public static ScheduleTag of(
            final long tagId,
            final long scheduleId
    ) {
        return new ScheduleTag(tagId, scheduleId);
    }

    public void updateTag(
            final long tagId,
            final LocalDateTime updatedAt
    ) {
        this.tagId = tagId;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public long getTagId() {
        return tagId;
    }

    public long getScheduleId() {
        return scheduleId;
    }
}