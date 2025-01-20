package com.official.memento.schedule.service;

import com.official.memento.schedule.service.command.ScheduleDeleteCommand;

@FunctionalInterface
public interface ScheduleDeleteUseCase {
    void delete(final ScheduleDeleteCommand command);
}
