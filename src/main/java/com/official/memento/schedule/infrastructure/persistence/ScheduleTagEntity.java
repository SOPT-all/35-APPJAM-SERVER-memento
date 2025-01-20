package com.official.memento.schedule.infrastructure.persistence;

import com.official.memento.global.entity.BaseTimeEntity;
import com.official.memento.schedule.domain.ScheduleTag;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "schedule_tag")
public class ScheduleTagEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private long tagId;
    private long scheduleId;

    private ScheduleTagEntity(final Long id, final long tagId, final long scheduleId, final LocalDateTime createdAt, final LocalDateTime updatedAt) {
        this.id = id;
        this.tagId = tagId;
        this.scheduleId = scheduleId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    private ScheduleTagEntity(final long tagId, final long scheduleId) {
        this.tagId = tagId;
        this.scheduleId = scheduleId;
    }

    protected ScheduleTagEntity() {
    }

    public static ScheduleTagEntity of(final ScheduleTag scheduleTag) {
        return new ScheduleTagEntity(
                scheduleTag.getTagId(),
                scheduleTag.getScheduleId()
        );
    }

    public static ScheduleTagEntity withId(final ScheduleTag scheduleTag) {
        return new ScheduleTagEntity(
                scheduleTag.getId(),
                scheduleTag.getTagId(),
                scheduleTag.getScheduleId(),
                scheduleTag.getCreatedAt(),
                scheduleTag.getUpdatedAt()
        );
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
