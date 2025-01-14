package com.official.memento.tag.controller.dto;

import com.official.memento.tag.domain.enums.TagColor;

public record TagCreateResponse(
         String name,
         TagColor color
) {
    public static TagCreateResponse of(String name, TagColor color) {
        return new TagCreateResponse(name, color);
    }
}
