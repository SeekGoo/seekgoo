package com.seekgu.config.exception;

public enum ErrorCode {

//    MEMBER 관련 5XXX ~
    NO_AUTH(5000),
    MEMBER_NOT_FOUND(5010),
    PASSWORD_NOT_MATCH(5011),
    ARGUMENT_NOT_VALID(5012),
    DUPLICATE_KEY(5013),

//  SEEKGU_BOARD 관련 6XXX ~
    BOARD_NOT_WRITE(6010),
    CANT_PARTICIPATE(6011),

//    REVIEW 관련 7XXX ~
    REVIEW_TOO_LONG(7000),
    NOT_A_REVIEWER(7011),

//  일반 관련
    UNKNOWN_ERROR(8010);

    private Integer code;

    ErrorCode(int code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}
