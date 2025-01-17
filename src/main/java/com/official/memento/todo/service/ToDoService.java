package com.official.memento.todo.service;

import com.official.memento.global.entity.enums.RepeatOption;
import com.official.memento.global.exception.NullPointException;
import com.official.memento.tag.domain.TagRepository;
import com.official.memento.todo.domain.ToDo;
import com.official.memento.todo.domain.ToDoRepository;
import com.official.memento.todo.domain.ToDoTag;
import com.official.memento.todo.domain.ToDoTagRepository;
import com.official.memento.todo.domain.enums.EisenhowerMatrixQuadrant;
import com.official.memento.todo.domain.enums.PriorityType;
import com.official.memento.todo.domain.enums.ToDoType;
import com.official.memento.todo.service.command.ToDoCreateCommand;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static com.official.memento.global.entity.enums.RepeatOption.DAILY;
import static com.official.memento.global.exception.ErrorCode.NULL_DATA_ERROR;
import static com.official.memento.todo.domain.enums.ToDoType.NORMAL;

@Service
public class ToDoService implements ToDoCreateUseCase {

    private final ToDoRepository toDoRepository;
    private final ToDoTagRepository toDoTagRepository;
    private final TagRepository tagRepository;

    public ToDoService(
            final ToDoRepository toDoRepository,
            final ToDoTagRepository toDoTagRepository,
            final TagRepository tagRepository
    ) {
        this.toDoRepository = toDoRepository;
        this.toDoTagRepository = toDoTagRepository;
        this.tagRepository = tagRepository;
    }

    @Override
    @Transactional
    public void create(final ToDoCreateCommand command) {
        String toDoGroupId = createGroupId();
        if (command.repeatExpiredDate() == null) {
            createSingleToDo(command, toDoGroupId);
        } else {
            createRepeatToDos(command, toDoGroupId);
        }
    }

    private void createSingleToDo(final ToDoCreateCommand command, final String toDoGroupId) {
        Double priorityValue = calculatePriorityValue(command.priorityUrgency(), command.priorityImportance());
        PriorityType priorityType = determinePriorityType(command.priorityUrgency(), command.priorityImportance());

        ToDo toDo = createToDo(command, toDoGroupId, priorityValue, priorityType, command.date());

        if (command.tagId() != null) {
            connectTag(command.tagId(), toDo);
        }
    }

    private void createRepeatToDos(final ToDoCreateCommand command, final String toDoGroupId) {
        LocalDate currentDate = command.date();
        LocalDate repeatExpiredDate = command.repeatExpiredDate();

        while (!currentDate.isAfter(repeatExpiredDate)) {
            Double priorityValue = calculatePriorityValue(command.priorityUrgency(), command.priorityImportance());
            PriorityType priorityType = determinePriorityType(command.priorityUrgency(), command.priorityImportance());

            ToDo toDo = createToDo(command, toDoGroupId, priorityValue, priorityType, currentDate);

            if (command.tagId() != null) {
                connectTag(command.tagId(), toDo);
            }

            if (command.repeatOption() == RepeatOption.DAILY) {
                currentDate = currentDate.plusDays(1);
            } else if (command.repeatOption() == RepeatOption.WEEKLY) {
                currentDate = currentDate.plusWeeks(1);
            } else if(command.repeatOption() == RepeatOption.MONTHLY) {
                currentDate = currentDate.plusMonths(1);
            } else {
                currentDate = currentDate.plusYears(1);
            }
        }
    }

    private ToDo createToDo(
            final ToDoCreateCommand command,
            final String toDoGroupId,
            final Double priorityValue,
            final PriorityType priorityType,
            final LocalDate date
    ) {
        return toDoRepository.save(ToDo.of(
                command.memberId(),
                toDoGroupId,
                date,
                command.description(),
                command.deadline(),
                false,
                command.repeatOption(),
                command.repeatExpiredDate(),
                command.priorityUrgency(),
                command.priorityImportance(),
                priorityValue,
                priorityType.name(),
                NORMAL
        ));
    }

    private String createGroupId() {
        return UUID.randomUUID().toString();
    }

    private Double calculatePriorityValue(Double priorityUrgency, Double priorityImportance) {
        if (priorityUrgency != null || priorityImportance != null) {
            return (priorityUrgency * 0.7) + (priorityImportance * 0.3);
        } else {
            throw new NullPointException(NULL_DATA_ERROR);
        }
    }

    private PriorityType determinePriorityType(Double priorityUrgency, Double priorityImportance) {
        return EisenhowerMatrixQuadrant.findQuadrant(priorityUrgency, priorityImportance).getPriorityType();
    }

    private void connectTag(final Long tagId, final ToDo toDo) {
        ToDoTag toDoTag = ToDoTag.of(tagId, toDo.getId());
        toDoTagRepository.save(toDoTag);
    }

    private int determineOrder() {
        // 순서 결정 로직 (예: 기본값은 0, 나중에 수정 가능)
        return 0;
    }
}
