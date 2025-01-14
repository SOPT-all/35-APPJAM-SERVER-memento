package com.official.memento.member.controller;

import com.official.memento.global.annotation.Authorization;
import com.official.memento.global.annotation.AuthorizationUser;
import com.official.memento.global.dto.ErrorResponse;
import com.official.memento.global.dto.SuccessResponse;
import com.official.memento.global.exception.ErrorCode;
import com.official.memento.member.controller.dto.MemberRequest;
import com.official.memento.member.controller.dto.MemberResponse;
import com.official.memento.member.service.MemberService;
import com.official.memento.member.service.command.MemberUpdateCommand;
import com.official.memento.member.service.usecase.MemberUpdateUseCase;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/members")
public class MemberApiController {

    private final MemberUpdateUseCase memberUpdateUseCase;

    public MemberApiController(MemberUpdateUseCase memberUpdateUseCase) {
        this.memberUpdateUseCase = memberUpdateUseCase;
    }

    @PutMapping("/personal-info")
    public ResponseEntity<?> updatePersonalInfo(
            //@Authorization AuthorizationUser authorizationUser, 로그인 구현되면 추가
            @RequestBody MemberRequest request
    ) {
        //test용, 로그인 구현되면 지우기
        AuthorizationUser authorizationUser = new AuthorizationUser(2L);

        try {
            MemberResponse response = memberUpdateUseCase.updatePersonalInfo(
                    new MemberUpdateCommand(
                            authorizationUser.memberId(),
                            request.wakeUpTime(),
                            request.windDownTime(),
                            request.job(),
                            request.jobOtherDetail(),
                            request.isStressedUnorganizedSchedule(),
                            request.isForgetImportantThings(),
                            request.isPreferReminder(),
                            request.isImportantBreaks()
                    )
            );

            return SuccessResponse.of(HttpStatus.OK, "회원 개인 정보 업데이트 성공", response);
        } catch (EntityNotFoundException e) {
            return ErrorResponse.of(HttpStatus.NOT_FOUND, ErrorCode.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            return ErrorResponse.of(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }
}