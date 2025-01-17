package com.official.memento.tag.controller;

import com.official.memento.global.annotation.Authorization;
import com.official.memento.global.annotation.AuthorizationUser;
import com.official.memento.global.dto.SuccessResponse;
import com.official.memento.tag.controller.dto.TagCreateRequest;
import com.official.memento.tag.controller.dto.TagCreateResponse;
import com.official.memento.tag.controller.dto.TagGetResponse;
import com.official.memento.tag.domain.Tag;
import com.official.memento.tag.service.TagCreateUseCase;
import com.official.memento.tag.service.TagGetUseCase;
import com.official.memento.tag.service.command.TagCreateCommand;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tags")
public class TagApiController implements TagApiDocs {

    private final TagCreateUseCase tagCreateUseCase;
    private final TagGetUseCase tagGetUseCase;

    public TagApiController(TagCreateUseCase tagCreateUseCase, TagGetUseCase tagGetUseCase) {
        this.tagCreateUseCase = tagCreateUseCase;
        this.tagGetUseCase = tagGetUseCase;
    }

    @Override
    @PostMapping
    public ResponseEntity<SuccessResponse<TagCreateResponse>> createTag(
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

    @Override
    @GetMapping
    public ResponseEntity<SuccessResponse<List<TagGetResponse>>> getTags(
            @Authorization final AuthorizationUser authorizationUser
    ) {
        final List<Tag> tags = tagGetUseCase.getTags(authorizationUser.memberId());
        return SuccessResponse.of(
                HttpStatus.OK,
                "Tag 조회 성공",
                tags.stream().map(tag -> TagGetResponse.of(tag.getId(), tag.getName(), tag.getColor().getHexCode())).toList()
        );
    }
}
