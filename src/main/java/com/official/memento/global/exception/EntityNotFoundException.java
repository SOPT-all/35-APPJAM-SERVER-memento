package com.official.memento.global.exception;

public class EntityNotFoundException extends MementoException {
    public EntityNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}
