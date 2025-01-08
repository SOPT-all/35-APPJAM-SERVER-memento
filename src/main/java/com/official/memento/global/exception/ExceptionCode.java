package com.official.memento.global.exception;

public enum ExceptionCode {
    ;

    private final int errorCode;
    private final String message;

    ExceptionCode(int errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getMessage() {
        return message;
    }
}
