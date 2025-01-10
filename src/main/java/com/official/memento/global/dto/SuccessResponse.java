package com.official.memento.global.dto;

import com.official.memento.global.exception.MementoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static com.official.memento.global.exception.ErrorCode.NULL_DATA_ERROR;

public record SuccessResponse<T>(
        String message,
        T data
) {
    public static <T> ResponseEntity<SuccessResponse<?>> of(final HttpStatus status, final String message, final T data) {
        if (status == null || message == null || data == null) {
            throw new MementoException(NULL_DATA_ERROR);
        }
        return ResponseEntity.status(status.value())
                .body(new SuccessResponse<T>(message, data));
    }

    public static ResponseEntity<SuccessResponse<?>> of(final HttpStatus status, final String message) {
        if (status == null || message == null) {
            throw new MementoException(NULL_DATA_ERROR);
        }
        return ResponseEntity.status(status.value())
                .body(new SuccessResponse<>(message, ""));
    }
}