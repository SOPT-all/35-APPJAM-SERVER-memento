package com.official.memento.schedule.infrastructure;

import com.official.memento.global.exception.EntityNotFoundException;
import com.official.memento.global.exception.ErrorCode;
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

    @Override
    public ScheduleTag findByScheduleId(final long scheduleId) {
        return scheduleTagEntityJpaRepository.findByScheduleId(scheduleId)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.NOT_FOUND_ENTITY));
    }

    @Override
    public void deleteByScheduleId(long scheduleId) {
        scheduleTagEntityJpaRepository.deleteByScheduleId(scheduleId);
    }

}
