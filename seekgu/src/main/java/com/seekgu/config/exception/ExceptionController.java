package com.seekgu.config.exception;

import com.seekgu.member.exception.AlreadyExistsIdOrNickNameException;
import com.seekgu.member.exception.NotExistUserException;
import com.seekgu.member.exception.NotMatchPasswordException;
import com.seekgu.seekguboard.exception.AlreadyDoneException;
import com.seekgu.seekguboard.exception.AlreadyParticipateException;
import com.seekgu.seekguboard.exception.SeekguBoardWriteException;
import com.seekgu.utils.ApiUtil;
import com.seekgu.utils.ApiUtil.ApiErrorResult;
import com.seekgu.utils.kakao.exception.KaKaoInvalidParsingException;
import com.seekgu.utils.slack.exception.SlackException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ExceptionController {

    @ExceptionHandler(NotMatchPasswordException.class)
    public ApiErrorResult<String> handleNotMatchPasswordException(NotMatchPasswordException e) {
        return ApiUtil.error(ErrorCode.PASSWORD_NOT_MATCH.getCode(), e.getMessage());
    }

    @ExceptionHandler(NotExistUserException.class)
    public ApiErrorResult<String> handleNotExistUserException(NotExistUserException e) {
        return ApiUtil.error(ErrorCode.MEMBER_NOT_FOUND.getCode(), e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiErrorResult<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        return ApiUtil.error(ErrorCode.ARGUMENT_NOT_VALID.getCode(), e.getMessage());
    }

    @ExceptionHandler(AlreadyExistsIdOrNickNameException.class)
    public ApiErrorResult<String> handleAlreadyExistsIdOrNickName(AlreadyExistsIdOrNickNameException e) {
        return ApiUtil.error(ErrorCode.DUPLICATE_KEY.getCode(), e.getMessage());
    }

    @ExceptionHandler(SeekguBoardWriteException.class)
    public ApiErrorResult<String> handleSeekguBoardWriteException(SeekguBoardWriteException e) {
        return ApiUtil.error(ErrorCode.BOARD_NOT_WRITE.getCode(), e.getMessage());
    }

    @ExceptionHandler({AlreadyParticipateException.class, AlreadyDoneException.class})
    public ApiErrorResult<String> handleParticipateException(RuntimeException e) {
        return ApiUtil.error(ErrorCode.CANT_PARTICIPATE.getCode(), e.getMessage());
    }

    @ExceptionHandler({RuntimeException.class, SlackException.class, KaKaoInvalidParsingException.class})
    public ApiErrorResult<String> handleRuntimeException(RuntimeException e) {
        log.error(e.getMessage());
        return ApiUtil.error(ErrorCode.UNKNOWN_ERROR.getCode(), "서버 동작과정에서 오류가 발생하였습니다.");
    }
}
