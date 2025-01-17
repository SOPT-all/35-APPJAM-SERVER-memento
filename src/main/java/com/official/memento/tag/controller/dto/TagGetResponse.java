package com.official.memento.tag.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "태그 응답")
public record TagGetResponse(
        @Schema(description = "태그 ID", example = "1")
        long id,
        @Schema(description = "태그 이름", example = "Mememnto")
        String name,
        @Schema(description = "색상 코드", example = "#FFFFFF")
        String colorCode
) {
    public static TagGetResponse of(long id, String name, String colorCode) {
        return new TagGetResponse(id, name, colorCode);
    }
}
