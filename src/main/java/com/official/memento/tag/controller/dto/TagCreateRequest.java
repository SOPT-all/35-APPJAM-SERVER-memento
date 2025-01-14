package com.official.memento.tag.controller.dto;

import com.official.memento.tag.domain.enums.TagColor;

public record TagCreateRequest(
        String name,
        TagColor color
) {
}
