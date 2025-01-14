// MemberUpdateUseCase.java
package com.official.memento.member.service.usecase;

import com.official.memento.member.controller.dto.MemberResponse;
import com.official.memento.member.service.command.MemberUpdateCommand;

@FunctionalInterface
public interface MemberUpdateUseCase {
    MemberResponse updatePersonalInfo(MemberUpdateCommand command);
}