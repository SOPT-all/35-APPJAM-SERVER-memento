package com.official.memento.tag.controller;

import com.official.memento.global.annotation.Authorization;
import com.official.memento.global.annotation.AuthorizationUser;
import com.official.memento.global.dto.SuccessResponse;
import com.official.memento.tag.controller.dto.TagCreateRequest;
import com.official.memento.tag.controller.dto.TagCreateResponse;
import com.official.memento.tag.controller.dto.TagGetResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Tag(name = "[Tag API] 태그 관련 API")
public interface TagApiDocs {

    @Operation(description = "태그 생성 API")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    ResponseEntity<SuccessResponse<TagCreateResponse>> createTag(
            @Authorization final AuthorizationUser authorizationUser,
            @RequestBody final TagCreateRequest request
    );

    @Operation(description = "태그 조회 API")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    ResponseEntity<SuccessResponse<List<TagGetResponse>>> getTags(
            @Authorization final AuthorizationUser authorizationUser
    );
}
