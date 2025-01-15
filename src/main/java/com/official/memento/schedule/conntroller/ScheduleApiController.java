package com.official.memento.schedule.conntroller;

import com.official.memento.global.annotation.Authorization;
import com.official.memento.global.annotation.AuthorizationUser;
import com.official.memento.global.dto.SuccessResponse;
import com.official.memento.schedule.conntroller.dto.request.ScheduleCreateRequest;
import com.official.memento.schedule.conntroller.dto.response.ScheduleCreateResponse;
import com.official.memento.schedule.service.ScheduleCreateUseCase;
import com.official.memento.schedule.service.command.ScheduleCreateCommand;
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

    public ScheduleApiController(ScheduleCreateUseCase scheduleCreateUseCase) {
        this.scheduleCreateUseCase = scheduleCreateUseCase;
    }

    @PostMapping
    ResponseEntity<SuccessResponse<ScheduleCreateResponse>> createSchedule(
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
                        scheduleCreateRequest.repeatOption(),
                        scheduleCreateRequest.repeatExpiredDate(),
                        scheduleCreateRequest.tagId()
                )
        );
        return SuccessResponse.of(
                HttpStatus.CREATED,
                "스케줄 생성 성공",
                ScheduleCreateResponse.of()
        );
    }
}