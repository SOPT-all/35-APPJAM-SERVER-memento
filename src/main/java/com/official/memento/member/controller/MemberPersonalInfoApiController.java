package com.official.memento.member.controller;

import com.official.memento.global.annotation.Authorization;
import com.official.memento.global.annotation.AuthorizationUser;
import com.official.memento.global.dto.SuccessResponse;
import com.official.memento.member.controller.dto.MemberPersonalInfoRequest;
import com.official.memento.member.domain.MemberPersonalInfo;
import com.official.memento.member.service.command.MemberPersonalInfoCommand;
import com.official.memento.member.service.usecase.MemberPersonalInfoUpdateUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/members/personal-info")
public class MemberPersonalInfoApiController implements MemberPersonalInfoApiDocs {
    private final MemberPersonalInfoUpdateUseCase memberPersonalInfoUseCase;

    public MemberPersonalInfoApiController(MemberPersonalInfoUpdateUseCase memberPersonalInfoUseCase) {
        this.memberPersonalInfoUseCase = memberPersonalInfoUseCase;
    }

    @PatchMapping
    @Override
    public ResponseEntity<SuccessResponse<?>> updatePersonalInfo(
            @Authorization final AuthorizationUser authorizationUser,
            @RequestBody final MemberPersonalInfoRequest request)
    {
        final MemberPersonalInfo personalInfo = memberPersonalInfoUseCase.update(
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
