package study.h2.common;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import study.h2.domain.member.exceptions.MemberNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MemberNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse HandleMemberNotFoundException(MemberNotFoundException e) {
        return new ErrorResponse(HttpStatus.BAD_REQUEST, e.getMessage());
    }

}
