package com.official.memento.schedule.service;

import com.official.memento.schedule.domain.Schedule;
import com.official.memento.schedule.domain.ScheduleRepository;
import com.official.memento.schedule.service.command.ScheduleCreateCommand;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.official.memento.schedule.domain.enums.ScheduleType.NORMAL;

@Service
public class ScheduleService implements ScheduleCreateUseCase {

    private final ScheduleRepository scheduleRepository;

    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    @Override
    @Transactional
    public Schedule create(final ScheduleCreateCommand command) {
        Schedule schedule = Schedule.of(
                command.memberId(),
                command.description(),
                command.startDate(),
                command.endDate(),
                command.isAllDay(),
                command.repeatOption(),
                command.repeatExpiredDate(),
                NORMAL,
                "일단 유니크 스케줄 그룹 아이디"
        );
        scheduleRepository.save(schedule);
        //순서관련 로직 추가
        return schedule;
    }

}
