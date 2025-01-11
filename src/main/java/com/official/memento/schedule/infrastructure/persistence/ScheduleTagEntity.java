package com.official.memento.schedule.infrastructure.persistence;

import com.official.memento.global.entity.BaseTimeEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "schedule_tag")
public class ScheduleTagEntity extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long tagId;
    private Long scheduleId;
}
