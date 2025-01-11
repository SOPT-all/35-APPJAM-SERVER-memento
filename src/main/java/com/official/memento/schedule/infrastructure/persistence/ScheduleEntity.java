package com.official.memento.schedule.infrastructure.persistence;

import com.official.memento.global.entity.BaseTimeEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "schedule")
public class ScheduleEntity extends BaseTimeEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

}
