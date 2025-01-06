package com.official.memento.member.presentation;

import com.official.memento.member.application.command.MemberNicknameUpdateCommand;
import com.official.memento.member.presentation.dto.MemberUpdateRequest;
import com.official.memento.member.application.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class MemberApiController {

    private final MemberService memberService;

    public MemberApiController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members")
    public String getMembers(
            @RequestParam String name
    ) {
        // memberService.getMember(name);
        return "members";
    }

    @PatchMapping("/members/nickname")
    public ResponseEntity updateMemberNickname(
            @RequestBody MemberUpdateRequest request
    ) {
        // 클라이언트 요청을 그대로 우리 서비스(application)에 넘겨줌
        MemberNicknameUpdateCommand command = new MemberNicknameUpdateCommand(request.nickname());
        memberService.updateNickname(command);
        return ResponseEntity.noContent().build();
    }


}
