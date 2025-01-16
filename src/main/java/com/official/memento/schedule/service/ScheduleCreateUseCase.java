package com.official.memento.schedule.service;

import com.official.memento.schedule.service.command.ScheduleCreateCommand;

@FunctionalInterface
public interface ScheduleCreateUseCase {
    void create(final ScheduleCreateCommand command);
}
