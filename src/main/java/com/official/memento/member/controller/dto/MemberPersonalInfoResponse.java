package com.official.memento.member.controller.dto;

import com.official.memento.member.domain.enums.JobType;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalTime;

@Schema(name = "사용자 개인화 정보 응답")
public record MemberPersonalInfoResponse(
        @Schema(description = "기상 시간", example = "06:00:00")
        LocalTime wakeUpTime,
        @Schema(description = "일정 마무리 시간", example = "22:00:00")
        LocalTime windDownTime,
        @Schema(description = "직업", example = "TECHNOLOGY,DATA_ANALYTICS,DESIGN_CREATIVITY,BUSINESS_MANAGEMENT,EDUCATION_TRAINING,HEALTHCARE_WELLNESS,FREELANCE_SELF_EMPLOYMENT,SERVICE_HOSPITALITY,ENGINEERING_MANUFACTURING,OTHER")
        JobType job,
        @Schema(description = "직업 기타 상세", example = "Backend Engineer")
        String jobOtherDetail,
        @Schema(description = "스트레스 여부", example = "true")
        Boolean isStressedUnorganizedSchedule,
        @Schema(description = "중요한 일을 잊는 경향", example = "true")
        Boolean isForgetImportantThings,
        @Schema(description = "리마인더 선호 여부", example = "true")
        Boolean isPreferReminder,
        @Schema(description = "중요한 휴식 여부", example = "true")
        Boolean isImportantBreaks
) {
    public static MemberPersonalInfoResponse of(
            final Long memberId,
            final LocalTime wakeUpTime,
            final LocalTime windDownTime,
            final JobType job,
            final String jobOtherDetail,
            final Boolean isStressedUnorganizedSchedule,
            final Boolean isForgetImportantThings,
            final Boolean isPreferReminder,
            final Boolean isImportantBreaks)
    {
        return new MemberPersonalInfoResponse(
                wakeUpTime,
                windDownTime,
                job,
                jobOtherDetail,
                isStressedUnorganizedSchedule,
                isForgetImportantThings,
                isPreferReminder,
                isImportantBreaks
        );
    }
}