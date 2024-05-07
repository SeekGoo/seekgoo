package com.seekgu.config.exception;

public enum ErrorCode {

    MEMBER_NOT_FOUND(5010),
    PASSWORD_NOT_MATCH(5011),
    ARGUMENT_NOT_VALID(5012),
    DUPLICATE_KEY(5013),

    UNKNOWN_ERROR(8010),;

    private Integer code;

    ErrorCode(int code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}
