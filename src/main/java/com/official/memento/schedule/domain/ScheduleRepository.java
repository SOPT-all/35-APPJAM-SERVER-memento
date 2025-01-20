package com.official.memento.schedule.domain;

import java.time.LocalDateTime;
import java.util.List;

public interface ScheduleRepository {
    Schedule save(final Schedule schedule);

    Schedule update(final Schedule schedule);

    Schedule findById(final long scheduleId);

    List<Schedule> findAllByScheduleGroupId(final String scheduleGroupId);

    List<Schedule> findAllByScheduleGroupIdAndStartDateGreaterThanEqual(final String scheduleGroupId, final LocalDateTime startDate);

    void deleteById(final long scheduleId);

    void deleteAll(final List<Schedule> schedules);
}
