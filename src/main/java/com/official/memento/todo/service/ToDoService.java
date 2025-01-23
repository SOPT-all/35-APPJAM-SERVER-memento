package com.official.memento.todo.service;

import com.official.memento.global.entity.enums.RepeatOption;
import com.official.memento.global.exception.ErrorCode;
import com.official.memento.global.exception.UnauthorizedException;
import com.official.memento.orderinfo.domain.OrderInfo;
import com.official.memento.orderinfo.domain.OrderInfoRepository;
import com.official.memento.orderinfo.domain.OrderWithScheduleOrToDo;
import com.official.memento.orderinfo.domain.PlanType;
import com.official.memento.tag.domain.Tag;
import com.official.memento.tag.domain.TagRepository;
import com.official.memento.todo.domain.ToDo;
import com.official.memento.todo.domain.ToDoRepository;
import com.official.memento.todo.domain.ToDoTag;
import com.official.memento.todo.domain.ToDoTagRepository;
import com.official.memento.todo.domain.enums.PriorityType;
import com.official.memento.todo.service.command.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.official.memento.todo.domain.enums.ToDoType.NORMAL;

@Service
public class ToDoService implements ToDoCreateUseCase, ToDoDeleteUseCase, ToDoUpdateUseCase, ToDoGetUseCase {

    private final ToDoRepository toDoRepository;
    private final ToDoTagRepository toDoTagRepository;
    private final TagRepository tagRepository;
    private final OrderInfoRepository orderInfoRepository;

    public ToDoService(ToDoRepository toDoRepository, ToDoTagRepository toDoTagRepository, TagRepository tagRepository, OrderInfoRepository orderInfoRepository) {
        this.toDoRepository = toDoRepository;
        this.toDoTagRepository = toDoTagRepository;
        this.tagRepository = tagRepository;
        this.orderInfoRepository = orderInfoRepository;
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

    @Override
    @Transactional
    public void delete(final ToDoDeleteCommand toDoDeleteCommand) {
        ToDo toDo = toDoRepository.findById(toDoDeleteCommand.toDoId());
        checkOwn(toDoDeleteCommand.memberId(), toDo);
        toDoRepository.deleteById(toDo.getId());
        toDoTagRepository.deleteByToDoId(toDo.getId());
        orderInfoRepository.deleteByToDoId(toDo.getId());
    }

    @Override
    @Transactional
    public void update(final ToDoUpdateCommand command) {
        ToDo toDo = toDoRepository.findById(command.toDoId());
        checkOwn(command.memberId(), toDo);
        PriorityType newPriorityType = toDo.getPriorityType();
        Double newPriorityValue = toDo.getPriorityValue();
        if (!Objects.equals(command.priorityUrgency(), toDo.getPriorityUrgency()) || !Objects.equals(command.priorityImportance(), toDo.getPriorityImportance())) {
            newPriorityType = determinePriorityType(command.priorityUrgency(), command.priorityImportance());
            newPriorityValue = calculatePriorityValue(command.priorityUrgency(), command.priorityImportance());
        }
        toDo.update(
                command.startDate(),
                command.description(),
                command.endDate(),
                command.priorityUrgency(),
                command.priorityImportance(),
                null,
                newPriorityType,
                newPriorityValue
        );
        toDoRepository.update(toDo);
        updateOrDeleteTag(toDo, command.tagId());
        if (toDo.getStartDate() != command.startDate() || toDo.getEndDate() != command.endDate()) {
            orderInfoRepository.deleteByToDoId(toDo.getId());
            assignOrder(command.startDate(), toDo);
        }
    }

    @Override
    @Transactional
    public boolean updateCompletion(final ToDoCompletionUpdateCommand command) {
        ToDo toDo = toDoRepository.findById(command.toDoId());
        checkOwn(command.memberId(), toDo);
        if (toDo.isCompleted()) {
            toDo.updateCompletion(false);
        } else {
            toDo.updateCompletion(true);
        }
        return toDoRepository.update(toDo).isCompleted();
    }

    @Override
    @Transactional
    public void updatePosition(final ToDoPositionUpdateCommand command) {
        ToDo toDo = toDoRepository.findById(command.toDoId());
        checkOwn(command.memberId(), toDo);

        LocalDate date = orderInfoRepository.findDateByToDoId(command.toDoId());
        int currentOrderNum = orderInfoRepository.findOrderByToDoId(command.toDoId());
        int targetOrderNum = command.targetOrderNum();

        if (currentOrderNum > targetOrderNum) {
            List<OrderInfo> ordersToUpdate = orderInfoRepository.findOrdersBetween(date, targetOrderNum, currentOrderNum - 1);
            for (OrderInfo order : ordersToUpdate) {
                order.incrementOrder();
                orderInfoRepository.update(order);
            }
        } else if (currentOrderNum < targetOrderNum) {
            List<OrderInfo> ordersToUpdate = orderInfoRepository.findOrdersBetween(date, currentOrderNum + 1, targetOrderNum);
            for (OrderInfo order : ordersToUpdate) {
                order.decrementOrder();
                orderInfoRepository.update(order);
            }
        }

        OrderInfo orderInfo = orderInfoRepository.findByToDoId(command.toDoId());
        orderInfo.setOrderNum(targetOrderNum);
        orderInfoRepository.update(orderInfo);
    }

    @Transactional(readOnly = true)
    @Override
    public List<ToDo> getToDos(final long memberId) {
        List<ToDo> todos = toDoRepository.findAllByMemberId(memberId);
        List<ToDo> toDos = todos.stream()
                .peek(todo -> {
                    Integer order = orderInfoRepository.findOrderByToDoId(todo.getId());
                    ToDoTag toDoTag = toDoTagRepository.findByToDoId(todo.getId());

                    if (toDoTag != null) {
                        Tag tag = tagRepository.findById(toDoTag.getTagId());
                        todo.updateTag(tag);
                    }

                    todo.updateOrderNum(order);
                })
                .toList();
        return sortToDosByStartDateAndOrder(toDos);
    }

    @Transactional(readOnly = true)
    @Override
    public List<ToDo> getTodosByDate(long memberId, LocalDate date) {
        List<ToDo> toDos = toDoRepository.findAllByMemberIdAndStartDate(memberId, date);
        toDos.forEach(todo -> {
            int orderNum = orderInfoRepository.findByToDoId(todo.getId()).getOrderNum();
            todo.update(
                    todo.getStartDate(),
                    todo.getDescription(),
                    todo.getEndDate(),
                    todo.getPriorityUrgency(),
                    todo.getPriorityImportance(),
                    orderNum,
                    todo.getPriorityType(),
                    todo.getPriorityValue()
            );
        });
        return toDos;
    }

    @Transactional(readOnly = true)
    @Override
    public ToDo getDetail(long memberId, long toDoId) {
        ToDo toDo = toDoRepository.findById(toDoId);
        checkOwn(memberId, toDo);
        ToDoTag toDoTag = toDoTagRepository.findByToDoId(toDoId);
        if (toDoTag != null) {
            Tag tag = tagRepository.findById(toDoTag.getTagId());
            toDo.updateTag(tag);
            toDo.updateTagId(toDoTag.getTagId());
        }
        return toDo;
    }



    private void createSingleToDo(final ToDoCreateCommand command, final String toDoGroupId) {
        Double priorityValue = calculatePriorityValue(command.priorityUrgency(), command.priorityImportance());
        PriorityType priorityType = determinePriorityType(command.priorityUrgency(), command.priorityImportance());
        ToDo toDo = createToDo(command, toDoGroupId, priorityValue, priorityType, command.startDate());

        if (command.tagId() != null) {
            connectTag(command.tagId(), toDo);
        }
        assignOrder(command.startDate(), toDo);
    }

    private void createRepeatToDos(final ToDoCreateCommand command, final String toDoGroupId) {
        LocalDate currentDate = command.startDate();
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
            } else if (command.repeatOption() == RepeatOption.MONTHLY) {
                currentDate = currentDate.plusMonths(1);
            } else {
                currentDate = currentDate.plusYears(1);
            }

            assignOrder(command.startDate(), toDo);
        }
    }

    private ToDo createToDo(
            final ToDoCreateCommand command,
            final String toDoGroupId,
            final Double priorityValue,
            final PriorityType priorityType,
            final LocalDate startDate
    ) {
        return toDoRepository.save(ToDo.of(
                command.memberId(),
                toDoGroupId,
                startDate,
                command.description(),
                command.endDate(),
                false,
                command.repeatOption(),
                command.repeatExpiredDate(),
                command.priorityUrgency(),
                command.priorityImportance(),
                priorityValue,
                priorityType,
                NORMAL
        ));
    }

    private String createGroupId() {
        return UUID.randomUUID().toString();
    }

    private Double calculatePriorityValue(Double priorityUrgency, Double priorityImportance) {
        if (priorityUrgency != null && priorityImportance != null) {
            return (priorityUrgency * 0.3) + (priorityImportance * 0.7);
        } else {
            return -1.0;
        }
    }

    private PriorityType determinePriorityType(Double priorityUrgency, Double priorityImportance) {
        return PriorityType.findPriorityType(priorityUrgency, priorityImportance);
    }

    private void connectTag(final Long tagId, final ToDo toDo) {
        ToDoTag toDoTag = ToDoTag.of(tagId, toDo.getId());
        toDoTagRepository.save(toDoTag);
    }

    private static void checkOwn(final long memberId, final ToDo toDo) {
        if (toDo.getMemberId() != memberId) {
            throw new UnauthorizedException(ErrorCode.UNAUTHORIZED_USER);
        }
    }

    private void updateOrDeleteTag(final ToDo toDo, final Long tagId) {
        ToDoTag toDoTag = toDoTagRepository.findByToDoId(toDo.getId());
        if (tagId == null) {
            toDoTagRepository.deleteByToDoId(toDo.getId());
        } else if (toDoTag == null) {
            toDoTag = ToDoTag.of(tagId, toDo.getId());
            toDoTagRepository.save(toDoTag);
        } else if (toDoTag.getTagId() != tagId) {
            toDoTag.updateTag(tagId, LocalDateTime.now());
            toDoTagRepository.update(toDoTag);
        }
    }

    private void assignOrder(LocalDate date, ToDo toDo) {
        List<OrderWithScheduleOrToDo> toDoList = orderInfoRepository.findOrderInfoWithDetails(date);
        int insertOrder = getInsertOrder(date, toDoList, toDo);
        createOrderInfo(date, toDo, insertOrder);
    }

    private int getInsertOrder(final LocalDate date, final List<OrderWithScheduleOrToDo> toDoList, final ToDo toDo) {
        int insertOrder = 1;
        boolean isInserted = false;
        for (OrderWithScheduleOrToDo existingOrder : toDoList) {

            if (!isInserted && existingOrder.getType() == PlanType.TODO) {
                if (toDo.getPriorityValue() > existingOrder.getPriorityValue()) {
                    insertOrder = existingOrder.getOrder();
                    isInserted = true;
                } else if (toDo.getPriorityValue().equals(existingOrder.getPriorityValue())) {
                    if (toDo.getCreatedAt().isBefore(existingOrder.getCreatedAt())) {
                        insertOrder = existingOrder.getOrder();
                        isInserted = true;
                    }
                }
            }

            if (isInserted) {
                existingOrder.shiftBack();
                orderInfoRepository.update(
                        OrderInfo.withId(
                                existingOrder.getOrderInfoId(),
                                existingOrder.getScheduleId(),
                                existingOrder.getToDoId(),
                                existingOrder.getOrder(),
                                date,
                                existingOrder.getType(),
                                existingOrder.getCreatedAt()
                        ));
            }
        }

        if (!isInserted) {
            insertOrder = toDoList.isEmpty() ? 1 : toDoList.get(toDoList.size() - 1).getOrder() + 1;
        }
        return insertOrder;
    }

    private void createOrderInfo(final LocalDate date, final ToDo toDo, final int insertOrder) {
        orderInfoRepository.save(OrderInfo.of(
                null,
                toDo.getId(),
                insertOrder,
                date,
                PlanType.TODO,
                LocalDateTime.now()
        ));
    }

    private List<ToDo> sortToDosByStartDateAndOrder(List<ToDo> toDos) {
        return toDos.stream()
                .sorted(Comparator.comparing(ToDo::getStartDate, Comparator.nullsLast(Comparator.naturalOrder()))
                        .thenComparing(ToDo::getOrderNum, Comparator.nullsLast(Comparator.naturalOrder())))
                .collect(Collectors.toList());
    }
}
