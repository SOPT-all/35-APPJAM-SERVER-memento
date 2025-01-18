package com.official.memento.todo.service;

import com.official.memento.todo.domain.ToDo;
import com.official.memento.todo.service.command.ToDoCreateCommand;
import com.official.memento.todo.service.command.ToDoDeleteCommand;
import org.springframework.transaction.annotation.Transactional;

public interface ToDoCreateUseCase {
    void create(ToDoCreateCommand command);
}
