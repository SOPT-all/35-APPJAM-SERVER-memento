// MemberUpdateUseCase.java
package com.official.memento.member.service.usecase;

import com.official.memento.member.controller.dto.MemberPersonalInfoResponse;
import com.official.memento.member.domain.MemberPersonalInfo;
import com.official.memento.member.service.command.MemberPersonalInfoCommand;

@FunctionalInterface
public interface MemberPersonalInfoUseCase {
    MemberPersonalInfo updatePersonalInfo(MemberPersonalInfoCommand command);
}