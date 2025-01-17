package com.official.memento.todo.service;

import com.official.memento.todo.domain.ToDo;
import com.official.memento.todo.service.command.ToDoCreateCommand;

@FunctionalInterface
public interface ToDoCreateUseCase {
    void create(ToDoCreateCommand command);
}
