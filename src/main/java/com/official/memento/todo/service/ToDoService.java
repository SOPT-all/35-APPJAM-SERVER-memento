package com.official.memento.todo.service;

import com.official.memento.schedule.domain.enums.RepeatOption;
import com.official.memento.todo.domain.ToDo;
import com.official.memento.todo.domain.ToDoRepository;
import com.official.memento.todo.service.command.ToDoCreateCommand;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.UUID;

@Service
public class ToDoService implements ToDoCreateUseCase {

    private final ToDoRepository toDoRepository;

    public ToDoService(ToDoRepository toDoRepository) {
        this.toDoRepository = toDoRepository;
    }

    @Override
    @Transactional
    public ToDo create(final ToDoCreateCommand command) {
        String bundleId = createBundleId();
        ToDo toDo = ToDo.of(
                command.memberId(),
                command.date(),
                command.description(),
                command.deadline(),
                command.isRepeated(),
                command.repeatOption(),
                command.repeatExpiredDate(),
                command.tagId(),
                command.priorityType(),
                bundleId()
        );
        return toDoRepository.save(toDo);
    }

    private String createBundleId() {
        return UUID.randomUUID().toString();
    }
}
