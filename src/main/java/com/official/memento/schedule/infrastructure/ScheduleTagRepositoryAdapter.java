package com.official.memento.schedule.infrastructure;

import com.official.memento.global.stereotype.Adapter;
import com.official.memento.schedule.domain.ScheduleTag;
import com.official.memento.schedule.domain.ScheduleTagRepository;
import com.official.memento.schedule.infrastructure.persistence.ScheduleTagEntity;
import com.official.memento.schedule.infrastructure.persistence.ScheduleTagEntityJpaRepository;

@Adapter
public class ScheduleTagRepositoryAdapter implements ScheduleTagRepository {

    private final ScheduleTagEntityJpaRepository scheduleTagEntityJpaRepository;

    public ScheduleTagRepositoryAdapter(ScheduleTagEntityJpaRepository scheduleTagEntityJpaRepository) {
        this.scheduleTagEntityJpaRepository = scheduleTagEntityJpaRepository;
    }

    @Override
    public ScheduleTag save(ScheduleTag scheduleTag) {
        ScheduleTagEntity scheduleTagEntity = scheduleTagEntityJpaRepository.save(ScheduleTagEntity.of(scheduleTag));
        return ScheduleTag.withId(
                scheduleTagEntity.getId(),
                scheduleTag.getTagId(),
                scheduleTag.getScheduleId()
        );
    }
}
