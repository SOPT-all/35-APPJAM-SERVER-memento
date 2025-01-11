package com.official.memento.global.dto;

import com.official.memento.global.exception.ErrorCode;
import com.official.memento.global.exception.MementoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static com.official.memento.global.exception.ErrorCode.NULL_DATA_ERROR;

public record ErrorResponse(
        ErrorCode errorCode
) {
    public static ResponseEntity<ErrorResponse> of(final HttpStatus status, final ErrorCode errorCode) {
        if (status == null || errorCode == null) {
            throw new MementoException(NULL_DATA_ERROR);
        }
        return ResponseEntity.status(status.value())
                .body(new ErrorResponse(errorCode));
    }
}