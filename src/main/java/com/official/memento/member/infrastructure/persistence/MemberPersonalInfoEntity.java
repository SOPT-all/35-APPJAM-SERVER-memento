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

    @OneToOne(fetch = FetchType.LAZY) // member 테이블과 1:1 관계 설정
    @JoinColumn(name = "member_id", nullable = false) // 외래 키 지정
    private MemberEntity member;

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
            final MemberEntity member,
            final LocalTime wakeUpTime,
            final LocalTime windDownTime,
            final JobType job,
            final String jobOtherDetail,
            final Boolean isStressedUnorganizedSchedule,
            final Boolean isForgetImportantThings,
            final Boolean isPreferReminder,
            final Boolean isImportantBreaks)
    {
        this.member = member;
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
            final MemberEntity member,
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
                member, wakeUpTime, windDownTime, job, jobOtherDetail,
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

    public MemberEntity getMember() {
        return member;
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