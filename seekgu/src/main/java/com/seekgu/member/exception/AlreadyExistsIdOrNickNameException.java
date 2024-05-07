package com.seekgu.member.exception;

public class AlreadyExistsIdOrNickNameException extends RuntimeException {
    public AlreadyExistsIdOrNickNameException(String message) {
        super(message);
    }
}
