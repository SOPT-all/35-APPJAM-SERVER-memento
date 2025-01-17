package com.official.memento.schedule.service;

import com.official.memento.global.entity.enums.RepeatOption;
import com.official.memento.schedule.domain.Schedule;
import com.official.memento.schedule.domain.ScheduleRepository;
import com.official.memento.schedule.domain.ScheduleTag;
import com.official.memento.schedule.domain.ScheduleTagRepository;
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

    public ScheduleService(
            final ScheduleRepository scheduleRepository,
            final ScheduleTagRepository scheduleTagRepository,
            final TagRepository tagRepository
    ) {
        this.scheduleRepository = scheduleRepository;
        this.scheduleTagRepository = scheduleTagRepository;
        this.tagRepository = tagRepository;
    }

    @Override
    @Transactional
    public void create(final ScheduleCreateCommand command) {
        String scheduleGroupId = UUID.randomUUID().toString();
        Schedule schedule = createSchedule(command, scheduleGroupId);
        if (command.tagId() != null) {
            connectTag(command.tagId(), schedule);
        }
        //순서관련 로직 추가
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
}
