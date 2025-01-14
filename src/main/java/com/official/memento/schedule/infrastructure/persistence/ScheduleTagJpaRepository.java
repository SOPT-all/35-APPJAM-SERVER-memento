package com.official.memento.schedule.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleTagJpaRepository extends JpaRepository<ScheduleTagEntity, Long> {
}
