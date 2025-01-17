package com.official.memento.global.exception;

public class EntityNotFoundException extends MementoException {
    public EntityNotFoundException(final ErrorCode errorCode) {
        super(errorCode);
    }
}
