package com.official.memento.todo.infrastructure;

import com.official.memento.global.stereotype.Adapter;
import com.official.memento.todo.domain.ToDo;
import com.official.memento.todo.domain.ToDoRepository;
import com.official.memento.todo.infrastructure.persistence.ToDoEntity;
import com.official.memento.todo.infrastructure.persistence.ToDoJpaRepository;

@Adapter
public class ToDoRepositoryAdapter implements ToDoRepository{

    private final ToDoJpaRepository toDoJpaRepository;

    public ToDoRepositoryAdapter(final ToDoJpaRepository toDoJpaRepository){
        this.toDoJpaRepository = toDoJpaRepository;
    }

    @Override
    public ToDo save(final ToDo toDo) {
        ToDoEntity toDoEntity = toDoJpaRepository.save(ToDoEntity.of(toDo));
        return ToDo.withId(
                toDoEntity.getId(),
                toDoEntity.getMemberId(),
                toDoEntity.getGroupId(),
                toDoEntity.getDate(),
                toDoEntity.getDescription(),
                toDoEntity.getDeadline(),
                toDoEntity.isCompleted(),
                toDoEntity.getRepeatOption(),
                toDoEntity.getRepeatExpiredDate(),
                toDoEntity.getPriorityUrgency(),
                toDoEntity.getPriorityImportance(),
                toDoEntity.getPriorityValue(),
                toDoEntity.getPriorityType(),
                toDoEntity.getType(),
                toDoEntity.getOrder()
        );
    }
}
