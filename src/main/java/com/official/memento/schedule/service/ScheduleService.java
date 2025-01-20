package com.official.memento.schedule.service;

import com.official.memento.global.entity.enums.RepeatOption;
import com.official.memento.orderinfo.domain.EventType;
import com.official.memento.orderinfo.domain.OrderInfo;
import com.official.memento.orderinfo.domain.OrderInfoRepository;
import com.official.memento.orderinfo.domain.OrderWithScheduleOrToDo;
import com.official.memento.schedule.domain.*;
import com.official.memento.schedule.service.command.RepeatScheduleCreateCommand;
import com.official.memento.schedule.service.command.ScheduleCreateCommand;
import com.official.memento.schedule.service.command.ScheduleDeleteAllCommand;
import com.official.memento.schedule.service.command.ScheduleDeleteCommand;
import com.official.memento.tag.domain.TagRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.official.memento.schedule.domain.enums.ScheduleType.NORMAL;

@Service
public class ScheduleService implements ScheduleCreateUseCase, RepeatScheduleCreateUseCase, ScheduleDeleteUseCase, ScheduleDeleteAllUseCase {

    private final ScheduleTagRepository scheduleTagRepository;
    private final ScheduleRepository scheduleRepository;
    private final TagRepository tagRepository;
    private final OrderInfoRepository orderInfoRepository;

    public ScheduleService(
            final ScheduleRepository scheduleRepository,
            final ScheduleTagRepository scheduleTagRepository,
            final TagRepository tagRepository,
            final OrderInfoRepository orderInfoRepository
    ) {
        this.scheduleRepository = scheduleRepository;
        this.scheduleTagRepository = scheduleTagRepository;
        this.tagRepository = tagRepository;
        this.orderInfoRepository = orderInfoRepository;
    }

    @Override
    @Transactional
    public void create(final ScheduleCreateCommand command) {
        String scheduleGroupId = UUID.randomUUID().toString();
        List<OrderWithScheduleOrToDo> scheduleList = orderInfoRepository.findOrderInfoWithDetails(
                command.startDate().toLocalDate()
        );
        Schedule schedule = createSchedule(command, scheduleGroupId);
        if (command.tagId() != null) {
            connectTag(command.tagId(), schedule);
        }
        int insertOrder = getInsertOrder(command.startDate().toLocalDate(), scheduleList, schedule);
        createOrderInfo(command.startDate().toLocalDate(), schedule, insertOrder);
    }

    @Override
    @Transactional
    public void createRepeat(final RepeatScheduleCreateCommand command) {
        String scheduleGroupId = UUID.randomUUID().toString();
        List<Schedule> schedules = null;
        switch (command.repeatOption()) {
            case DAILY -> schedules = createRepeatSchedules(command, scheduleGroupId, 1);
            case WEEKLY -> schedules = createRepeatSchedules(command, scheduleGroupId, 7);
            case YEARLY -> schedules = createRepeatSchedules(command, scheduleGroupId, 365);
            default ->
                    throw new IllegalArgumentException("Unsupported repeat option: " + command.repeatOption());//커스텀 익셉션으로 교체 예정
        }
        if (command.tagId() != null) {
            connectTags(command.tagId(), schedules);
        }
        //순서관련 로직 추가
    }

    @Override
    @Transactional
    public void delete(final ScheduleDeleteCommand scheduleDeleteCommand) {
        Schedule schedule = scheduleRepository.findById(scheduleDeleteCommand.scheduleId());
        checkOwn(scheduleDeleteCommand.memberId(), schedule);
        scheduleRepository.deleteById(schedule.getId());
        //태그 삭제
        //순서 관련 삭제
    }

    @Override
    @Transactional
    public void deleteAll(final ScheduleDeleteAllCommand scheduleDeleteAllCommand) {
        Schedule schedule = scheduleRepository.findById(scheduleDeleteAllCommand.scheduleId());
        checkOwn(scheduleDeleteAllCommand.memberId(), schedule);
        belongsToGroup(scheduleDeleteAllCommand.scheduleGroupId(), schedule);
        scheduleRepository.deleteAllByGroupId(scheduleDeleteAllCommand.scheduleGroupId());
        //태그 삭제
        //순서 관련 삭제
    }

    private Schedule createSchedule(final ScheduleCreateCommand command, final String scheduleGroupId) {
        return scheduleRepository.save(Schedule.of(
                command.memberId(),
                command.description(),
                command.startDate(),
                command.endDate(),
                command.isAllDay(),
                RepeatOption.NONE,
                null,
                NORMAL,
                scheduleGroupId
        ));
    }

    private List<Schedule> createRepeatSchedules(
            final RepeatScheduleCreateCommand command,
            final String scheduleGroupId,
            final int afterStartDay
    ) {
        List<Schedule> schedules = new ArrayList<>();
        LocalDateTime currentStartDate = command.startDate();
        LocalDateTime currentEndDate = command.endDate();
        LocalDate repeatExpiredDate = command.repeatExpiredDate();
        while (!currentEndDate.toLocalDate().isAfter(repeatExpiredDate)) {
            Schedule schedule = scheduleRepository.save(Schedule.of(
                    command.memberId(),
                    command.description(),
                    currentStartDate,
                    currentEndDate,
                    command.isAllDay(),
                    command.repeatOption(),
                    repeatExpiredDate,
                    NORMAL,
                    scheduleGroupId
            ));
            schedules.add(schedule);
            currentStartDate = currentStartDate.plusDays(afterStartDay);
            currentEndDate = currentEndDate.plusDays(afterStartDay);
        }
        return schedules;
    }

    private void connectTags(final Long tagId, final List<Schedule> schedules) {
        schedules.forEach(schedule -> connectTag(tagId, schedule));
    }

    private void connectTag(final Long tagId, final Schedule schedule) {
        tagRepository.findById(tagId);
        ScheduleTag scheduleTag = ScheduleTag.of(tagId, schedule.getId());
        scheduleTagRepository.save(scheduleTag);
    }

    private static void checkOwn(final long memberId, final Schedule schedule) {
        if (schedule.getMemberId() != memberId) {
            throw new IllegalArgumentException("해당 스케줄을 소유하지 않음");//커스텀으로 바꿔야함
        }
    }

    private static void belongsToGroup(String scheduleGroupId, Schedule schedule) {
        if (!schedule.getScheduleGroupId().equals(scheduleGroupId)) {
            throw new IllegalArgumentException("해당 스케줄의 그룹 아이디와 일치하지 않습니다."); //커스텀
        }
    }

    private int getInsertOrder(final LocalDate date, final List<OrderWithScheduleOrToDo> scheduleList, final Schedule schedule) {
        int insertOrder = 1;
        boolean isInserted = false;
        for (OrderWithScheduleOrToDo existingOrder : scheduleList) {

            //순서정보중 스케줄의 시간을 고려하여 삽입해야할 위치 선정
            if (!isInserted && existingOrder.getType() == EventType.Schedule) {
                if (schedule.getStartDate().equals(existingOrder.getStartDate()) && schedule.getEndDate().isBefore(existingOrder.getEndDate())) {
                    insertOrder = existingOrder.getOrder();
                    isInserted = true;
                } else if (schedule.getStartDate().isBefore(existingOrder.getStartDate())) {
                    insertOrder = existingOrder.getOrder();
                    isInserted = true;
                }
            }

            //삽입 된 곳 이후의 순서정보들을 1씩 증가
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

        //위 로직에 걸리지 않았을 경우 -> 빈리스트거나 가장 마지막에 추가되는 순서정보
        if (!isInserted) {
            insertOrder = scheduleList.isEmpty() ? 1 : scheduleList.get(scheduleList.size() - 1).getOrder() + 1;
        }
        return insertOrder;
    }

    private void createOrderInfo(final LocalDate date, final Schedule schedule, final int insertOrder) {
        orderInfoRepository.save(OrderInfo.of(
                schedule.getId(),
                null,
                insertOrder,
                date,
                EventType.Schedule,
                LocalDateTime.now()
        ));
    }
}
