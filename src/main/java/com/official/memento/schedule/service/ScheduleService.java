package com.official.memento.schedule.service;

import com.official.memento.schedule.domain.Schedule;
import com.official.memento.schedule.domain.ScheduleRepository;
import com.official.memento.schedule.domain.ScheduleTag;
import com.official.memento.schedule.domain.ScheduleTagRepository;
import com.official.memento.schedule.service.command.ScheduleCreateCommand;
import com.official.memento.tag.domain.TagRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.official.memento.schedule.domain.enums.ScheduleType.NORMAL;

@Service
public class ScheduleService implements ScheduleCreateUseCase {

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
    public Schedule create(final ScheduleCreateCommand command) {
        Schedule schedule = createSchedule(command);
        connectTag(command, schedule);
        //순서관련 로직 추가
        return schedule;
    }

    private void connectTag(final ScheduleCreateCommand command, final Schedule schedule) {
        tagRepository.findById(command.tagId());
        ScheduleTag scheduleTag = ScheduleTag.of(command.tagId(), schedule.getId());
        scheduleTagRepository.save(scheduleTag);
    }

    private Schedule createSchedule(final ScheduleCreateCommand command) {
        return scheduleRepository.save(Schedule.of(
                command.memberId(),
                command.description(),
                command.startDate(),
                command.endDate(),
                command.isAllDay(),
                command.repeatOption(),
                command.repeatExpiredDate(),
                NORMAL,
                "일단 유니크 스케줄 그룹 아이디"
        ));
    }
}
