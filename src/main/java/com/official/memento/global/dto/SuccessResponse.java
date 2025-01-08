package com.official.memento.global.dto;

public record SuccessResponse<T>(
        String message,
        T data
) {
    public static <T> SuccessResponse<T> success(final String message, final T data) {
        return new SuccessResponse<T>( message, data);
    }

    public static SuccessResponse<?> success(final String message) {
        return new SuccessResponse<>( message, "");
    }
}