package com.official.memento.member.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "사용자 업타임 조회 응답")
public record MemberUptimeResponse(
        @Schema(description = "기상 시간", example = "06:30")
        String wakeUpTime,
        @Schema(description = "일정 마무리 시간", example = "22:30")
        String windDownTime
) {
    public static MemberUptimeResponse of(String wakeUpTime, String windDownTime) {
        return new MemberUptimeResponse(wakeUpTime, windDownTime);
    }
}
