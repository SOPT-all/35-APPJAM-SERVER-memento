package com.official.memento.member.domain.port;

import com.official.memento.member.domain.MemberPersonalInfo;

import java.util.Optional;

public interface MemberPersonalInfoRepository {

    Optional<MemberPersonalInfo> findByMemberId(final Long memberId);

    MemberPersonalInfo create(final MemberPersonalInfo memberPersonalInfo);

    MemberPersonalInfo update(final MemberPersonalInfo memberPersonalInfo);
}