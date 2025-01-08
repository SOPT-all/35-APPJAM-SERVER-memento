package com.official.memento.global.dto;

public record ErrorResponse(
        int code
) {
    public static ErrorResponse of(int code) {
        return new ErrorResponse( code);
    }
}
