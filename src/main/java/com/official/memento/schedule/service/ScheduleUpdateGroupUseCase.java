package com.official.memento.schedule.service;

import com.official.memento.schedule.service.command.ScheduleUpdateGroupCommand;

@FunctionalInterface
public interface ScheduleUpdateGroupUseCase {
    void updateGroup(final ScheduleUpdateGroupCommand command);
}
