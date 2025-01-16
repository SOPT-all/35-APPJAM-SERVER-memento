package com.official.memento.schedule.service;

import com.official.memento.schedule.service.command.RepeatScheduleCreateCommand;

@FunctionalInterface
public interface RepeatScheduleCreateUseCase {
    void createRepeat(final RepeatScheduleCreateCommand command);
}
