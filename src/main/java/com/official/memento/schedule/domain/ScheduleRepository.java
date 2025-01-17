package com.official.memento.schedule.domain;

public interface ScheduleRepository {
    Schedule save(final Schedule schedule);

    Schedule findById(final long scheduleId);

    void deleteById(final long scheduleId);

    void deleteAllByGroupId(final String groupId);
}
