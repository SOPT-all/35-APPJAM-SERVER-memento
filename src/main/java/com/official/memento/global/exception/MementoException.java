package com.official.memento.global.exception;

public class MementoException extends RuntimeException {

    private final int errorCode;
    private final String errorMessage;

    public MementoException(ExceptionCode exceptionCode) {
        checkNull(exceptionCode.getMessage());
        this.errorCode = exceptionCode.getErrorCode();
        this.errorMessage = exceptionCode.getMessage();
    }

    private void checkNull(Object o){
        if (o == null) {
            throw new NullPointerException("에러 메세지가 비어있습니다.");
        }
    }
}
