package com.official.memento.member.controller;

import com.official.memento.global.annotation.Authorization;
import com.official.memento.global.annotation.AuthorizationUser;
import com.official.memento.global.dto.ErrorResponse;
import com.official.memento.global.dto.SuccessResponse;
import com.official.memento.member.controller.dto.MemberPersonalInfoRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "[MemberPersonalInfo] 사용자 개인화 정보 관련 API")
public interface MemberPersonalInfoApiDocs {

    @Operation(summary = "사용자 개인화 정보 업데이트")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "회원 개인 정보 업데이트 성공"),
                    @ApiResponse(responseCode = "400", description = "회원 개인 정보 업데이트 실패", content = @Content),
                    @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR", content = @Content)
            }
    )
    ResponseEntity<SuccessResponse<?>> updatePersonalInfo(
            @Parameter(name = "Authorization", in = ParameterIn.HEADER, description = "Bearer Token", required = true, example = "Bearer access_token")
            @Authorization final AuthorizationUser authorizationUser,
            @RequestBody final MemberPersonalInfoRequest request);
}
