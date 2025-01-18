package com.official.memento.auth.controller;

import com.official.memento.auth.controller.dto.AuthApiRequest;
import com.official.memento.auth.controller.dto.AuthApiResponse;
import com.official.memento.auth.domain.AuthorizationMember;
import com.official.memento.auth.service.AuthResult;
import com.official.memento.auth.service.AuthService;
import com.official.memento.auth.service.command.AuthCommand;
import com.official.memento.global.dto.SuccessResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
public class AuthApiController {

    private final AuthService authService;

    public AuthApiController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/api/v1/auth/login")
    public ResponseEntity<SuccessResponse<AuthApiResponse>> login(@Valid @RequestBody final AuthApiRequest request) {
        final AuthCommand command = AuthCommand.of(request.provider(), request.idToken());
        final AuthResult authResult = authService.authenticate(command);

        final AuthApiResponse response = new AuthApiResponse(
                "소셜 로그인 성공",
                authResult.getAccessToken().getToken(), // Access Token
                authResult.getMember().getAuth().getRefreshToken(),
                authResult.isNewUser()
        );
        return SuccessResponse.of(HttpStatus.OK, "소셜 로그인 성공", response);
    }
}
