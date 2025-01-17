package com.official.memento.todo.infrastructure;

import com.official.memento.global.stereotype.Adapter;
import com.official.memento.todo.domain.ToDo;
import com.official.memento.todo.domain.ToDoTag;
import com.official.memento.todo.domain.ToDoTagRepository;
import com.official.memento.todo.infrastructure.persistence.ToDoTagEntity;
import com.official.memento.todo.infrastructure.persistence.ToDoTagJpaRepository;

@Adapter
public class ToDoTagRepositoryAdapter implements ToDoTagRepository {

    private final ToDoTagJpaRepository toDoTagJpaRepository;

    public ToDoTagRepositoryAdapter(ToDoTagJpaRepository toDoTagJpaRepository){
        this.toDoTagJpaRepository = toDoTagJpaRepository;
    }

    @Override
    public ToDoTag save(ToDoTag toDoTag){
        ToDoTagEntity toDoTagEntity = toDoTagJpaRepository.save(ToDoTagEntity.of(toDoTag));
        return ToDoTag.withId(
                toDoTagEntity.getId(),
                toDoTag.getTagId(),
                toDoTag.getToDoId()
        );
    }
}
