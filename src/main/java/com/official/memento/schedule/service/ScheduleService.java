package com.official.memento.schedule.service;

import com.official.memento.global.entity.enums.RepeatOption;
import com.official.memento.schedule.domain.Schedule;
import com.official.memento.schedule.domain.ScheduleRepository;
import com.official.memento.schedule.domain.ScheduleTag;
import com.official.memento.schedule.domain.ScheduleTagRepository;
import com.official.memento.schedule.service.command.*;
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
public class ScheduleService implements
        ScheduleCreateUseCase,
        RepeatScheduleCreateUseCase,
        ScheduleDeleteUseCase,
        ScheduleDeleteGroupUseCase,
        ScheduleUpdateUseCase,
        ScheduleUpdateGroupUseCase {

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
        //Todo 순서관련 로직 추가
    }

    @Override
    @Transactional
    public void createRepeat(final RepeatScheduleCreateCommand command) {
        String scheduleGroupId = UUID.randomUUID().toString();
        List<Schedule> schedules = createRepeatSchedules(
                command.memberId(),
                command.description(),
                command.startDate(),
                command.endDate(),
                command.repeatOption(),
                command.repeatExpiredDate(),
                command.isAllDay(),
                scheduleGroupId
        );
        if (command.tagId() != null) {
            connectTags(command.tagId(), schedules);
        }
        //Todo 순서관련 로직 추가
    }

    @Override
    @Transactional
    public void delete(final ScheduleDeleteCommand command) {
        Schedule schedule = scheduleRepository.findById(command.scheduleId());
        checkOwn(command.memberId(), schedule);
        scheduleRepository.deleteById(schedule.getId());
        scheduleTagRepository.deleteByScheduleId(schedule.getId());
        //Todo 순서 관련 삭제
    }

    @Override
    @Transactional
    public void deleteGroup(final ScheduleDeleteGroupCommand command) {
        Schedule schedule = scheduleRepository.findById(command.scheduleId());
        checkOwn(command.memberId(), schedule);
        belongsToGroup(command.scheduleGroupId(), schedule);
        List<Schedule> targetSchedules = scheduleRepository.findAllByScheduleGroupIdAndStartDateGreaterThanEqual(command.scheduleGroupId(), schedule.getStartDate());
        targetSchedules.forEach(targetSchedule -> removeTagConnection(targetSchedule.getId()));
        scheduleRepository.deleteAll(targetSchedules);
        //Todo 순서 관련 삭제
    }

    @Override
    @Transactional
    public void update(final ScheduleUpdateCommand command) {
        Schedule schedule = scheduleRepository.findById(command.scheduleId());
        checkOwn(command.memberId(), schedule);
        schedule.update(
                command.description(),
                command.startDate(),
                command.endDate(),
                command.isAllDay()
        );
        scheduleRepository.update(schedule);
        updateOrDeleteTag(schedule, command.tagId());
    }

    @Override
    @Transactional
    public void updateGroup(ScheduleUpdateGroupCommand command) {
        Schedule schedule = scheduleRepository.findById(command.scheduleId());
        checkOwn(command.memberId(), schedule);
        belongsToGroup(command.scheduleGroupId(), schedule);
        List<Schedule> oldSchedules = scheduleRepository.findAllByScheduleGroupIdAndStartDateGreaterThanEqual(
                command.scheduleGroupId(),
                schedule.getStartDate()
        );
        oldSchedules.forEach(oldSchedule -> removeTagConnection(oldSchedule.getId()));
        List<Schedule> newSchedules = createRepeatSchedules(
                command.memberId(),
                command.description(),
                command.startDate(),
                command.endDate(),
                command.repeatOption(),
                command.repeatExpiredDate(),
                command.isAllDay(),
                schedule.getScheduleGroupId()
        );
        if (command.tagId() != null) {
            connectTags(command.tagId(), newSchedules);
        }
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
            final long memberId,
            final String description,
            final LocalDateTime startDate,
            final LocalDateTime endDate,
            final RepeatOption repeatOption,
            final LocalDate repeatExpiredDate,
            final boolean isAllDay,
            final String scheduleGroupId
    ) {
        List<Schedule> schedules = new ArrayList<>();
        LocalDateTime currentStartDate = startDate;
        LocalDateTime currentEndDate = endDate;
        while (!currentEndDate.toLocalDate().isAfter(repeatExpiredDate)) {
            Schedule schedule = scheduleRepository.save(Schedule.of(
                    memberId,
                    description,
                    currentStartDate,
                    currentEndDate,
                    isAllDay,
                    repeatOption,
                    repeatExpiredDate,
                    NORMAL,
                    scheduleGroupId
            ));
            schedules.add(schedule);
            switch (repeatOption) {
                case DAILY -> {
                    currentStartDate = currentStartDate.plusDays(1);
                    currentEndDate = currentEndDate.plusDays(1);
                }
                case WEEKLY -> {
                    currentStartDate = currentStartDate.plusWeeks(1);
                    currentEndDate = currentEndDate.plusWeeks(1);
                }
                case MONTHLY -> {
                    currentStartDate = currentStartDate.plusMonths(1);
                    currentEndDate = currentEndDate.plusMonths(1);
                }
                case YEARLY -> {
                    currentStartDate = currentStartDate.plusYears(1);
                    currentEndDate = currentEndDate.plusYears(1);
                }
                default ->
                        throw new IllegalArgumentException("Unsupported repeat option: " + repeatOption);//Todo 커스텀 익셉션으로 교체 예정
            }
        }
        return schedules;
    }

    private void updateOrDeleteTag(final Schedule schedule, final Long tagId) {
        ScheduleTag scheduleTag = scheduleTagRepository.findByScheduleId(schedule.getId());
        if (tagId == null) {
            scheduleTagRepository.deleteByScheduleId(schedule.getId());
        } else if (scheduleTag == null) {
            scheduleTag = ScheduleTag.of(tagId, schedule.getId());
            scheduleTagRepository.save(scheduleTag);
        } else if (scheduleTag.getTagId() != tagId) {
            scheduleTag.updateTag(tagId, scheduleTag.getUpdatedAt());
            scheduleTagRepository.update(scheduleTag);
        } else {
            throw new IllegalArgumentException("예상치 못한 오류 발생"); //Todo 커스텀 오류 변경 예정
        }
    }

    private void saveOrUpdateTag(ScheduleTag scheduleTag, final long scheduleId, final long tagId) {
        if (scheduleTag == null) {
            scheduleTag = ScheduleTag.of(tagId, scheduleId);
            scheduleTagRepository.save(scheduleTag);
        } else {
            scheduleTag.updateTag(tagId, scheduleTag.getUpdatedAt());
            scheduleTagRepository.save(scheduleTag);
        }

    }

    private void connectTags(final Long tagId, final List<Schedule> schedules) {
        schedules.forEach(schedule -> connectTag(tagId, schedule));
    }

    private void connectTag(final Long tagId, final Schedule schedule) {
        tagRepository.findById(tagId);
        ScheduleTag scheduleTag = ScheduleTag.of(tagId, schedule.getId());
        scheduleTagRepository.save(scheduleTag);
    }

    private void removeTagConnection(final long scheduleId) {
        scheduleTagRepository.deleteByScheduleId(scheduleId);
    }

    private static void checkOwn(final long memberId, final Schedule schedule) { //Todo Schedule안으로 리팩토링
        if (schedule.getMemberId() != memberId) {
            throw new IllegalArgumentException("해당 스케줄을 소유하지 않음");//Todo 커스텀으로 바꿔야함
        }
    }

    private static void belongsToGroup(String scheduleGroupId, Schedule schedule) { //Todo 스케줄 안으로 리팩토링
        if (!schedule.getScheduleGroupId().equals(scheduleGroupId)) {
            throw new IllegalArgumentException("해당 스케줄의 그룹 아이디와 일치하지 않습니다."); //Todo 커스텀
        }
    }
}
