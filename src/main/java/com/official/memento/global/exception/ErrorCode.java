package com.official.memento.global.exception;

public enum ErrorCode {

    /*빈 데이터*/
    NULL_DATA_ERROR("필수 입력 데이터가 누락되었습니다."),

    /* 서버 내부  에러 */
    INTERNAL_SERVER_ERROR("서버 내부에서 에러가 발생했습니다."),

    /* 존재하지 않는 데이터 */
    NOT_FOUND_ENTITY("찾을 수 없는 데이터입니다.");

    private final String message;

    ErrorCode(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
