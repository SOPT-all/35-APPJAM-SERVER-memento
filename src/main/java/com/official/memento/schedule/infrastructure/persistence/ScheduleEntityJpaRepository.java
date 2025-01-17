package com.official.memento.schedule.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleEntityJpaRepository extends JpaRepository<ScheduleEntity, Long> {
    void deleteAllByScheduleGroupId(final String groupId);
}
