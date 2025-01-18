package com.official.memento.member.controller;

import com.official.memento.global.annotation.Authorization;
import com.official.memento.global.annotation.AuthorizationUser;
import com.official.memento.global.dto.SuccessResponse;
import com.official.memento.member.controller.dto.MemberPersonalInfoRequest;
import com.official.memento.member.controller.dto.MemberUptimeResponse;
import com.official.memento.member.domain.MemberPersonalInfo;
import com.official.memento.member.service.command.MemberPersonalInfoCommand;
import com.official.memento.member.service.usecase.MemberPersonalInfoUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/members/personal-info")
public class MemberPersonalInfoApiController implements MemberPersonalInfoApiDocs {
    private final MemberPersonalInfoUseCase memberPersonalInfoUseCase;

    public MemberPersonalInfoApiController(MemberPersonalInfoUseCase memberPersonalInfoUseCase) {
        this.memberPersonalInfoUseCase = memberPersonalInfoUseCase;
    }

    @PatchMapping
    @Override
    public ResponseEntity<SuccessResponse<?>> updatePersonalInfo(
            @Authorization final AuthorizationUser authorizationUser,
            @RequestBody final MemberPersonalInfoRequest request) {
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

    @GetMapping("/uptime")
    public ResponseEntity<SuccessResponse<MemberUptimeResponse>> getUpTime(
            @Authorization final AuthorizationUser authorizationUser) {
        final MemberUptimeResponse response = memberPersonalInfoUseCase.retrieveUptime(authorizationUser.memberId());
        return SuccessResponse.of(HttpStatus.OK, "사용자 업타임 조회 성공", response);
    }
}
