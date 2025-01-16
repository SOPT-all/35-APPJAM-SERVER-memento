package com.official.memento.auth.service.usecase;

import com.official.memento.auth.domain.AuthorizationMember;
import com.official.memento.auth.service.command.AuthCommand;

public interface AuthUseCase {
    AuthorizationMember authenticate(AuthCommand command);
}
