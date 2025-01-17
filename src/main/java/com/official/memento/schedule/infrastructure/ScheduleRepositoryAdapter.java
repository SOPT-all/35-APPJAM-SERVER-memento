package com.official.memento.schedule.infrastructure;

import com.official.memento.global.stereotype.Adapter;
import com.official.memento.schedule.domain.Schedule;
import com.official.memento.schedule.domain.ScheduleRepository;
import com.official.memento.schedule.infrastructure.persistence.ScheduleEntity;
import com.official.memento.schedule.infrastructure.persistence.ScheduleEntityJpaRepository;

@Adapter
public class ScheduleRepositoryAdapter implements ScheduleRepository {

    private final ScheduleEntityJpaRepository scheduleEntityJpaRepository;

    public ScheduleRepositoryAdapter(final ScheduleEntityJpaRepository scheduleEntityJpaRepository) {
        this.scheduleEntityJpaRepository = scheduleEntityJpaRepository;
    }

    @Override
    public Schedule save(final Schedule schedule) {
        ScheduleEntity scheduleEntity = scheduleEntityJpaRepository.save(ScheduleEntity.of(schedule));
        return Schedule.withId(
                scheduleEntity.getId(),
                scheduleEntity.getMemberId(),
                scheduleEntity.getDescription(),
                scheduleEntity.getStartDate(),
                scheduleEntity.getEndDate(),
                scheduleEntity.isAllDay(),
                scheduleEntity.getRepeatOption(),
                scheduleEntity.getRepeatExpiredDate(),
                scheduleEntity.getType(),
                schedule.getScheduleGroupId()
        );
    }
}
