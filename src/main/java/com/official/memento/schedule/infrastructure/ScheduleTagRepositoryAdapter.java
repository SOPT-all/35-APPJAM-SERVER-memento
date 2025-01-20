package com.official.memento.schedule.infrastructure;

import com.official.memento.global.stereotype.Adapter;
import com.official.memento.schedule.domain.ScheduleTag;
import com.official.memento.schedule.domain.ScheduleTagRepository;
import com.official.memento.schedule.infrastructure.persistence.ScheduleTagEntity;
import com.official.memento.schedule.infrastructure.persistence.ScheduleTagEntityJpaRepository;

import java.util.Optional;

@Adapter
public class ScheduleTagRepositoryAdapter implements ScheduleTagRepository {

    private final ScheduleTagEntityJpaRepository scheduleTagEntityJpaRepository;

    public ScheduleTagRepositoryAdapter(ScheduleTagEntityJpaRepository scheduleTagEntityJpaRepository) {
        this.scheduleTagEntityJpaRepository = scheduleTagEntityJpaRepository;
    }

    @Override
    public ScheduleTag save(final ScheduleTag scheduleTag) {
        ScheduleTagEntity scheduleTagEntity = scheduleTagEntityJpaRepository.save(ScheduleTagEntity.of(scheduleTag));
        return ScheduleTag.withId(
                scheduleTagEntity.getId(),
                scheduleTag.getTagId(),
                scheduleTag.getScheduleId(),
                scheduleTag.getCreatedAt(),
                scheduleTag.getUpdatedAt()
        );
    }

    @Override
    public ScheduleTag update(final ScheduleTag scheduleTag) {
        ScheduleTagEntity scheduleTagEntity = scheduleTagEntityJpaRepository.save(ScheduleTagEntity.withId(scheduleTag));
        return ScheduleTag.withId(
                scheduleTagEntity.getId(),
                scheduleTag.getTagId(),
                scheduleTag.getScheduleId(),
                scheduleTag.getCreatedAt(),
                scheduleTag.getUpdatedAt()
        );
    }

    @Override
    public ScheduleTag findByScheduleId(final long scheduleId) {
        Optional<ScheduleTagEntity> scheduleTagEntity = scheduleTagEntityJpaRepository.findByScheduleId(scheduleId);
        return scheduleTagEntity.map(scheduleTag -> ScheduleTag.withId(
                scheduleTag.getId(),
                scheduleTag.getTagId(),
                scheduleTag.getScheduleId(),
                scheduleTag.getCreatedAt(),
                scheduleTag.getUpdatedAt()
        )).orElse(null);
    }

    @Override
    public void deleteByScheduleId(long scheduleId) {
        scheduleTagEntityJpaRepository.deleteByScheduleId(scheduleId);
    }

}
