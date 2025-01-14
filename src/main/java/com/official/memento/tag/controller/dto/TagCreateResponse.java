package com.official.memento.tag.controller.dto;

import com.official.memento.tag.domain.enums.TagColor;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "태그 생성 응답")
public record TagCreateResponse(
        @Schema(description = "태그 이름", example = "Mememnto")
        String name,
        @Schema(description = "색상 코드", example = "#FFFFFF")
        String colorCode
) {
    public static TagCreateResponse of(String name, TagColor color) {
        return new TagCreateResponse(name, color.getHexCode());
    }
}
