package com.official.memento.global.exception;

public class MementoException extends RuntimeException {

    private final ErrorCode errorCode;

    public MementoException(final ErrorCode errorCode) {
        checkNull(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    private void checkNull(Object o){
        if (o == null) {
            throw new NullPointerException("에러 메세지가 비어있습니다.");
        }
    }
}
