package com.official.memento.schedule.conntroller;

import com.official.memento.global.dto.SuccessResponse;
import com.official.memento.schedule.conntroller.dto.request.RepeatScheduleCreateRequest;
import com.official.memento.schedule.conntroller.dto.request.ScheduleCreateRequest;
import com.official.memento.schedule.conntroller.dto.request.ScheduleUpdateGroupRequest;
import com.official.memento.schedule.conntroller.dto.request.ScheduleUpdateRequest;
import com.official.memento.schedule.service.*;
import com.official.memento.schedule.service.command.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/schedules")
public class ScheduleApiController {

    private final ScheduleCreateUseCase scheduleCreateUseCase;
    private final RepeatScheduleCreateUseCase repeatScheduleCreateUseCase;
    private final ScheduleDeleteUseCase scheduleDeleteUseCase;
    private final ScheduleDeleteGroupUseCase scheduleDeleteGroupUseCase;
    private final ScheduleUpdateUseCase scheduleUpdateUseCase;
    private final ScheduleUpdateGroupUseCase scheduleUpdateGroupUseCase;

    public ScheduleApiController(
            final ScheduleCreateUseCase scheduleCreateUseCase,
            final RepeatScheduleCreateUseCase repeatScheduleCreateUseCase,
            final ScheduleDeleteUseCase scheduleDeleteUseCase,
            final ScheduleDeleteGroupUseCase scheduleDeleteGroupUseCase,
            final ScheduleUpdateUseCase scheduleUpdateUseCase,
            final ScheduleUpdateGroupUseCase scheduleUpdateGroupUseCase
    ) {

        this.scheduleCreateUseCase = scheduleCreateUseCase;
        this.repeatScheduleCreateUseCase = repeatScheduleCreateUseCase;
        this.scheduleDeleteUseCase = scheduleDeleteUseCase;
        this.scheduleDeleteGroupUseCase = scheduleDeleteGroupUseCase;
        this.scheduleUpdateUseCase = scheduleUpdateUseCase;
        this.scheduleUpdateGroupUseCase = scheduleUpdateGroupUseCase;
    }

    @PostMapping
    ResponseEntity<SuccessResponse<?>> createSchedule(
            //@Authorization final AuthorizationUser authorizationUser,
            @RequestBody final ScheduleCreateRequest scheduleCreateRequest
    ) {
        scheduleCreateUseCase.create(
                ScheduleCreateCommand.of(
                        1,
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
            //@Authorization final AuthorizationUser authorizationUser,
            @RequestBody final RepeatScheduleCreateRequest repeatScheduleCreateRequest
    ) {
        repeatScheduleCreateUseCase.createRepeat(
                RepeatScheduleCreateCommand.of(
                        1,
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

    @DeleteMapping("/{scheduleId}")
    ResponseEntity<SuccessResponse<?>> deleteSchedule(
            //@Authorization final AuthorizationUser authorizationUser,
            @PathVariable final long scheduleId
    ) {
        scheduleDeleteUseCase.delete(ScheduleDeleteCommand.of(1, scheduleId));
        return SuccessResponse.of(
                HttpStatus.OK,
                "단일 스케줄 삭제 성공"
        );
    }

    @DeleteMapping("/{scheduleId}/group")
    ResponseEntity<SuccessResponse<?>> deleteScheduleGroup(
            //@Authorization final AuthorizationUser authorizationUser,
            @PathVariable final long scheduleId,
            @RequestParam final String scheduleGroupId
    ) {
        scheduleDeleteGroupUseCase.deleteGroup(ScheduleDeleteGroupCommand.of(1, scheduleId, scheduleGroupId));
        return SuccessResponse.of(
                HttpStatus.OK,
                "반복 스케줄 삭제 성공"
        );
    }

    @PatchMapping("/{scheduleId}")
    ResponseEntity<SuccessResponse<?>> updateSchedule(
            //@Authorization final AuthorizationUser authorizationUser,
            @PathVariable final long scheduleId,
            @RequestBody final ScheduleUpdateRequest scheduleUpdateRequest
    ) {
        scheduleUpdateUseCase.update(ScheduleUpdateCommand.of(
                1,
                scheduleId,
                scheduleUpdateRequest.description(),
                scheduleUpdateRequest.startDate(),
                scheduleUpdateRequest.endDate(),
                scheduleUpdateRequest.isAllDay(),
                scheduleUpdateRequest.tagId()
        ));
        return SuccessResponse.of(
                HttpStatus.OK,
                "단일 스케줄 업데이트 성공"
        );
    }

    @PatchMapping("/{scheduleId}/group")
    ResponseEntity<SuccessResponse<?>> updateScheduleGroup(
            //@Authorization final AuthorizationUser authorizationUser,
            @PathVariable final long scheduleId,
            @RequestBody final ScheduleUpdateGroupRequest scheduleUpdateGroupRequest
    ) {
        scheduleUpdateGroupUseCase.updateGroup(ScheduleUpdateGroupCommand.of(
                1,
                scheduleId,
                scheduleUpdateGroupRequest.description(),
                scheduleUpdateGroupRequest.startDate(),
                scheduleUpdateGroupRequest.endDate(),
                scheduleUpdateGroupRequest.repeatOption(),
                scheduleUpdateGroupRequest.repeatExpiredDate(),
                scheduleUpdateGroupRequest.isAllDay(),
                scheduleUpdateGroupRequest.tagId(),
                scheduleUpdateGroupRequest.scheduleGroupId()
        ));
        return SuccessResponse.of(
                HttpStatus.OK,
                "반복 스케줄 업데이트 성공"
        );
    }
}