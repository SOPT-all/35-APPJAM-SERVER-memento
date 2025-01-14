package com.official.memento.tag.service.command;

import com.official.memento.tag.domain.enums.TagColor;

public record TagCreateCommand(
        Long memberId,
        TagColor color,
        String name
) {
    public static TagCreateCommand of(
            Long memberId,
            TagColor color,
            String name
    ) {
        return new TagCreateCommand(memberId, color, name);
    }
}
