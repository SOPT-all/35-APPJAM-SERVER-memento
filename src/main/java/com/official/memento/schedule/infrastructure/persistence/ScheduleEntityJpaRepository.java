package com.official.memento.schedule.infrastructure.persistence;

import com.official.memento.schedule.domain.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ScheduleEntityJpaRepository extends JpaRepository<ScheduleEntity, Long> {
    List<ScheduleEntity> findAllByScheduleGroupIdAndStartDateGreaterThanEqual(final String groupId, final LocalDateTime startDate);
    List<ScheduleEntity> findAllByScheduleGroupId(final String groupId);
    void deleteAll(List<Schedule> schedules);
}
