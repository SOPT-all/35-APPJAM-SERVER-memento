package com.official.memento.schedule.domain;

public class ScheduleTag {
    private Long id;
    private long tagId;
    private long scheduleId;

    public ScheduleTag(final Long id, final long tagId, final long scheduleId) {
        this.id = id;
        this.tagId = tagId;
        this.scheduleId = scheduleId;
    }

    public ScheduleTag(final long tagId, final long scheduleId) {
        this.tagId = tagId;
        this.scheduleId = scheduleId;
    }

    public static ScheduleTag withId(
            final Long id,
            final long tagId,
            final long scheduleId
    ) {
        return new ScheduleTag(id, tagId, scheduleId);
    }

    public static ScheduleTag of(
            final long tagId,
            final long scheduleId
    ) {
        return new ScheduleTag(tagId, scheduleId);
    }

    public void updateTag(
            final long tagId
    ){
        this.tagId = tagId;
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