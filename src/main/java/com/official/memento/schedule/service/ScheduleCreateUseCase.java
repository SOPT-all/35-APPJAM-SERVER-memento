package com.official.memento.schedule.service;

import com.official.memento.schedule.domain.Schedule;
import com.official.memento.schedule.service.command.ScheduleCreateCommand;

@FunctionalInterface
public interface ScheduleCreateUseCase {
    Schedule create(ScheduleCreateCommand command);
}
