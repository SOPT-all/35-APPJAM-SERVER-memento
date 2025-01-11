package com.official.memento.member.infrastructure.persistence;


import jakarta.persistence.*;

import java.time.LocalTime;

@Entity
@Table(name = "member_personal_info")
public class MemberPersonalInfoEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long memberId;

    private LocalTime wakeUpTime;

    private LocalTime windDownTime;

    private String jobOtherDetail;

    private Boolean isStressedUnorganizedSchedule;

    private Boolean isForgetImportantThings;

    private Boolean isPreferReminder;

    private Boolean isImportantBreaks;
}
