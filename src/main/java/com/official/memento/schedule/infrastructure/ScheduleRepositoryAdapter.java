package com.official.memento.schedule.infrastructure;

import com.official.memento.global.stereotype.Adapter;
import com.official.memento.schedule.domain.Schedule;
import com.official.memento.schedule.domain.ScheduleRepository;
import com.official.memento.schedule.infrastructure.persistence.ScheduleEntity;
import com.official.memento.schedule.infrastructure.persistence.ScheduleEntityJpaRepository;

@Adapter
public class ScheduleRepositoryAdapter implements ScheduleRepository {

    private final ScheduleEntityJpaRepository scheduleEntityJpaRepository;

    public ScheduleRepositoryAdapter(ScheduleEntityJpaRepository scheduleEntityJpaRepository) {
        this.scheduleEntityJpaRepository = scheduleEntityJpaRepository;
    }

    @Override
    public Schedule save(Schedule schedule) {
        ScheduleEntity scheduleEntity = ScheduleEntity.of(schedule);
        return null;
    }
}
