package com.official.memento.member.service.usecase;

import com.official.memento.member.domain.MemberPersonalInfo;
import com.official.memento.member.service.command.MemberPersonalInfoCommand;

@FunctionalInterface
public interface MemberPersonalInfoUpdateUseCase {
    MemberPersonalInfo update(final MemberPersonalInfoCommand command);
}