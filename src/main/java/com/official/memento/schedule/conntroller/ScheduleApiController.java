package com.official.memento.schedule.conntroller;

import com.official.memento.global.annotation.Authorization;
import com.official.memento.global.annotation.AuthorizationUser;
import com.official.memento.global.dto.SuccessResponse;
import com.official.memento.schedule.conntroller.dto.request.ScheduleCreateRequest;
import com.official.memento.schedule.conntroller.dto.request.RepeatScheduleCreateRequest;
import com.official.memento.schedule.service.RepeatScheduleCreateUseCase;
import com.official.memento.schedule.service.ScheduleCreateUseCase;
import com.official.memento.schedule.service.command.ScheduleCreateCommand;
import com.official.memento.schedule.service.command.RepeatScheduleCreateCommand;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/schedule")
public class ScheduleApiController {

    private final ScheduleCreateUseCase scheduleCreateUseCase;
    private final RepeatScheduleCreateUseCase repeatScheduleCreateUseCase;

    public ScheduleApiController(
            final ScheduleCreateUseCase scheduleCreateUseCase,
            final RepeatScheduleCreateUseCase repeatScheduleCreateUseCase
    ) {
        this.scheduleCreateUseCase = scheduleCreateUseCase;
        this.repeatScheduleCreateUseCase = repeatScheduleCreateUseCase;
    }

    @PostMapping
    ResponseEntity<SuccessResponse<?>> createSchedule(
            @Authorization final AuthorizationUser authorizationUser,
            @RequestBody final ScheduleCreateRequest scheduleCreateRequest
    ) {
        scheduleCreateUseCase.create(
                ScheduleCreateCommand.of(
                        authorizationUser.memberId(),
                        scheduleCreateRequest.description(),
                        scheduleCreateRequest.startDate(),
                        scheduleCreateRequest.endDate(),
                        scheduleCreateRequest.isAllDay(),
                        scheduleCreateRequest.tagId()
                )
        );
        return SuccessResponse.of(
                HttpStatus.CREATED,
                "단일 스케줄 생성 성공"
        );
    }

    @PostMapping("/repetition")
    ResponseEntity<SuccessResponse<?>> createScheduleMultiple(
            @Authorization final AuthorizationUser authorizationUser,
            @RequestBody final RepeatScheduleCreateRequest repeatScheduleCreateRequest
    ) {
        repeatScheduleCreateUseCase.createRepeat(
                RepeatScheduleCreateCommand.of(
                        authorizationUser.memberId(),
                        repeatScheduleCreateRequest.description(),
                        repeatScheduleCreateRequest.startDate(),
                        repeatScheduleCreateRequest.endDate(),
                        repeatScheduleCreateRequest.isAllDay(),
                        repeatScheduleCreateRequest.repeatOption(),
                        repeatScheduleCreateRequest.repeatExpiredDate(),
                        repeatScheduleCreateRequest.tagId()
                )
        );
        return SuccessResponse.of(
                HttpStatus.CREATED,
                "반복 스케줄 생성 성공"
        );
    }
}