package com.official.memento.schedule.service;

import com.official.memento.schedule.service.command.ScheduleDeleteGroupCommand;

@FunctionalInterface
public interface ScheduleDeleteGroupUseCase {
    void deleteGroup(final ScheduleDeleteGroupCommand command);
}
