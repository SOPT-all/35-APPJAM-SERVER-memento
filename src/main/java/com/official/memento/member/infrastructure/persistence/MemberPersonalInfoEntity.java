package com.official.memento.member.infrastructure.persistence;

import com.official.memento.member.domain.enums.JobType;
import jakarta.persistence.*;

import java.time.LocalTime;

@Entity
@Table(name = "member_personal_info")
public class MemberPersonalInfoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long memberId;

    private LocalTime wakeUpTime;

    private LocalTime windDownTime;

    @Enumerated(EnumType.STRING)
    private JobType job;

    private String jobOtherDetail;

    private Boolean isStressedUnorganizedSchedule;

    private Boolean isForgetImportantThings;

    private Boolean isPreferReminder;

    private Boolean isImportantBreaks;

    public MemberPersonalInfoEntity() {
    }

    private MemberPersonalInfoEntity(
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
        this.memberId = memberId;
        this.wakeUpTime = wakeUpTime;
        this.windDownTime = windDownTime;
        this.job = job;
        this.jobOtherDetail = jobOtherDetail;
        this.isStressedUnorganizedSchedule = isStressedUnorganizedSchedule;
        this.isForgetImportantThings = isForgetImportantThings;
        this.isPreferReminder = isPreferReminder;
        this.isImportantBreaks = isImportantBreaks;
    }

    public static MemberPersonalInfoEntity of(
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
        return new MemberPersonalInfoEntity(
                memberId, wakeUpTime, windDownTime, job, jobOtherDetail,
                isStressedUnorganizedSchedule, isForgetImportantThings,
                isPreferReminder, isImportantBreaks
        );
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Long getMemberId() {
        return memberId;
    }

    public LocalTime getWakeUpTime() {
        return wakeUpTime;
    }

    public LocalTime getWindDownTime() {
        return windDownTime;
    }

    public JobType getJob() {
        return job;
    }

    public String getJobOtherDetail() {
        return jobOtherDetail;
    }

    public Boolean getIsStressedUnorganizedSchedule() {
        return isStressedUnorganizedSchedule;
    }

    public Boolean getIsForgetImportantThings() {
        return isForgetImportantThings;
    }

    public Boolean getIsPreferReminder() {
        return isPreferReminder;
    }

    public Boolean getIsImportantBreaks() {
        return isImportantBreaks;
    }

    // 추가된 updateFields 메서드
    public void updatePersonalInfo(
            final LocalTime wakeUpTime,
            final LocalTime windDownTime,
            final JobType job,
            final String jobOtherDetail,
            final Boolean isStressedUnorganizedSchedule,
            final Boolean isForgetImportantThings,
            final Boolean isPreferReminder,
            final Boolean isImportantBreaks) {
        this.wakeUpTime = wakeUpTime;
        this.windDownTime = windDownTime;
        this.job = job;
        this.jobOtherDetail = jobOtherDetail;
        this.isStressedUnorganizedSchedule = isStressedUnorganizedSchedule;
        this.isForgetImportantThings = isForgetImportantThings;
        this.isPreferReminder = isPreferReminder;
        this.isImportantBreaks = isImportantBreaks;
    }
}