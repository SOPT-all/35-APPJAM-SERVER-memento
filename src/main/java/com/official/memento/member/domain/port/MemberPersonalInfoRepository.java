package com.official.memento.member.domain.port;

import com.official.memento.member.domain.MemberPersonalInfo;

public interface MemberPersonalInfoRepository {

    MemberPersonalInfo findByMemberId(Long memberId);

    MemberPersonalInfo save(MemberPersonalInfo memberPersonalInfo);
}