package com.official.memento.tag.controller;

import com.official.memento.global.annotation.Authorization;
import com.official.memento.global.annotation.AuthorizationUser;
import com.official.memento.global.dto.SuccessResponse;
import com.official.memento.tag.controller.dto.TagCreateRequest;
import com.official.memento.tag.controller.dto.TagCreateResponse;
import com.official.memento.tag.domain.Tag;
import com.official.memento.tag.service.TagCreateUseCase;
import com.official.memento.tag.service.command.TagCreateCommand;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/tags")
public class TagApiController {

    private final TagCreateUseCase tagCreateUseCase;

    public TagApiController(TagCreateUseCase tagCreateUseCase) {
        this.tagCreateUseCase = tagCreateUseCase;
    }

    @PostMapping
    public ResponseEntity<SuccessResponse<?>> createTag(
            @Authorization final AuthorizationUser authorizationUser,
            @RequestBody final TagCreateRequest request
    ) {
        Tag tag = tagCreateUseCase.create(
                new TagCreateCommand(
                        authorizationUser.memberId(),
                        request.color(),
                        request.name()
                )
        );
        return SuccessResponse.of(
               HttpStatus.OK,
                "Tag 생성 성공",
                TagCreateResponse.of(tag.getName(), tag.getColor())
        );
    }
}
