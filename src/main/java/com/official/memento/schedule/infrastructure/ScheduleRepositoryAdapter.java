package com.official.memento.schedule.infrastructure;

import com.official.memento.global.exception.EntityNotFoundException;
import com.official.memento.global.exception.ErrorCode;
import com.official.memento.global.stereotype.Adapter;
import com.official.memento.schedule.domain.Schedule;
import com.official.memento.schedule.domain.ScheduleRepository;
import com.official.memento.schedule.infrastructure.persistence.ScheduleEntity;
import com.official.memento.schedule.infrastructure.persistence.ScheduleEntityJpaRepository;

import java.time.LocalDateTime;
import java.util.List;

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
                scheduleEntity.getScheduleGroupId(),
                scheduleEntity.getCreatedAt(),
                scheduleEntity.getUpdatedAt()
        );
    }

    @Override
    public Schedule update(final Schedule schedule) {
        ScheduleEntity scheduleEntity = scheduleEntityJpaRepository.save(ScheduleEntity.withId(schedule));
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
                scheduleEntity.getScheduleGroupId(),
                scheduleEntity.getCreatedAt(),
                scheduleEntity.getUpdatedAt()
        );
    }

    @Override
    public Schedule findById(long scheduleId) {
        ScheduleEntity scheduleEntity = scheduleEntityJpaRepository.findById(scheduleId)
                .orElseThrow(
                        () -> new EntityNotFoundException(ErrorCode.NOT_FOUND_ENTITY)
                );
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
                scheduleEntity.getScheduleGroupId(),
                scheduleEntity.getCreatedAt(),
                scheduleEntity.getUpdatedAt()
        );
    }

    @Override
    public List<Schedule> findAllByScheduleGroupId(final String scheduleGroupId) {
        List<ScheduleEntity> scheduleEntities = scheduleEntityJpaRepository.findAllByScheduleGroupId(scheduleGroupId);
        return scheduleEntities.stream().map(scheduleEntity -> Schedule.withId(
                scheduleEntity.getId(),
                scheduleEntity.getMemberId(),
                scheduleEntity.getDescription(),
                scheduleEntity.getStartDate(),
                scheduleEntity.getEndDate(),
                scheduleEntity.isAllDay(),
                scheduleEntity.getRepeatOption(),
                scheduleEntity.getRepeatExpiredDate(),
                scheduleEntity.getType(),
                scheduleEntity.getScheduleGroupId(),
                scheduleEntity.getCreatedAt(),
                scheduleEntity.getUpdatedAt()
        )).toList();
    }

    @Override
    public List<Schedule> findAllByScheduleGroupIdAndStartDateGreaterThanEqual(String scheduleGroupId, LocalDateTime startDate) {
        List<ScheduleEntity> scheduleEntities = scheduleEntityJpaRepository.findAllByScheduleGroupIdAndStartDateGreaterThanEqual(scheduleGroupId, startDate);
        return scheduleEntities.stream().map(scheduleEntity -> Schedule.withId(
                scheduleEntity.getId(),
                scheduleEntity.getMemberId(),
                scheduleEntity.getDescription(),
                scheduleEntity.getStartDate(),
                scheduleEntity.getEndDate(),
                scheduleEntity.isAllDay(),
                scheduleEntity.getRepeatOption(),
                scheduleEntity.getRepeatExpiredDate(),
                scheduleEntity.getType(),
                scheduleEntity.getScheduleGroupId(),
                scheduleEntity.getCreatedAt(),
                scheduleEntity.getUpdatedAt()
        )).toList();
    }

    @Override
    public void deleteById(final long scheduleId) {
        scheduleEntityJpaRepository.deleteById(scheduleId);
    }

    @Override
    public void deleteAll(final List<Schedule> schedules) {
        List<ScheduleEntity> scheduleEntities = schedules.stream().map(ScheduleEntity::withId).toList();
        scheduleEntityJpaRepository.deleteAll(scheduleEntities);
    }
}
