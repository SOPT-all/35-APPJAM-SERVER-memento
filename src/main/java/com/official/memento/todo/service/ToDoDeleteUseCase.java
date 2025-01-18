package com.official.memento.todo.service;

import com.official.memento.todo.service.command.ToDoDeleteCommand;

@FunctionalInterface
public interface ToDoDeleteUseCase {
    void delete(final ToDoDeleteCommand toDoDeleteCommand);
}
