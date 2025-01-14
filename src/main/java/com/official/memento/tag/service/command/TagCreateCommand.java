package com.official.memento.tag.service.command;

import com.official.memento.tag.domain.enums.TagColor;

public record TagCreateCommand(
        Long memberId,
        TagColor color,
        String name
) {
}
