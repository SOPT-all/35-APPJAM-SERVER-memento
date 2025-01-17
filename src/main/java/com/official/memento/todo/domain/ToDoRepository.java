package com.official.memento.todo.domain;

import com.official.memento.todo.infrastructure.persistence.ToDoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ToDoRepository {
    ToDo save(final ToDo toDo);
}
