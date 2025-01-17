package com.official.memento.schedule.service;

import com.official.memento.schedule.service.command.ScheduleDeleteAllCommand;

@FunctionalInterface
public interface ScheduleDeleteAllUseCase {
    void deleteAll(final ScheduleDeleteAllCommand scheduleDeleteAllCommand);
}
