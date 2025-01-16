package com.official.memento.member.controller;

import com.official.memento.global.annotation.AuthorizationUser;
import com.official.memento.global.dto.ErrorResponse;
import com.official.memento.global.dto.SuccessResponse;
import com.official.memento.global.exception.ErrorCode;
import com.official.memento.member.controller.dto.MemberPersonalInfoRequest;
import com.official.memento.member.controller.dto.MemberPersonalInfoResponse;
import com.official.memento.member.domain.MemberPersonalInfo;
import com.official.memento.member.service.command.MemberPersonalInfoCommand;
import com.official.memento.member.service.usecase.MemberPersonalInfoUseCase;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/members/personal-info")
public class MemberApiController {

    private final MemberPersonalInfoUseCase memberPersonalInfoUseCase;

    public MemberApiController(final MemberPersonalInfoUseCase memberPersonalInfoUseCase) {
        this.memberPersonalInfoUseCase = memberPersonalInfoUseCase;
    }

    @PatchMapping
    public ResponseEntity<?> updatePersonalInfo(
            //@Authorization final AuthorizationUser authorizationUser,
            @RequestBody final MemberPersonalInfoRequest request)
    {
        //test용, 로그인 구현되면 지우기
        final AuthorizationUser authorizationUser = new AuthorizationUser(2L);
        final MemberPersonalInfo personalInfo = memberPersonalInfoUseCase.updatePersonalInfo(
                new MemberPersonalInfoCommand(
                        authorizationUser.memberId(),
                        request.wakeUpTime(),
                        request.windDownTime(),
                        request.job(),
                        request.jobOtherDetail(),
                        request.isStressedUnorganizedSchedule(),
                        request.isForgetImportantThings(),
                        request.isPreferReminder(),
                        request.isImportantBreaks())
        );
        return SuccessResponse.of(HttpStatus.OK, "회원 개인 정보 업데이트 성공");
    }
}