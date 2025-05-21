package com.soft_arex.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice

public class Handler {
    @ExceptionHandler(UserException.class)
    @ResponseBody
    public ResponseEntity<ExceptionResponse> userException(final UserException e) {
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.setMessage(e.getMessage());
        exceptionResponse.setCode(e.getCode());
        HttpStatus status = e.getStatus() != null ? e.getStatus() : HttpStatus.BAD_REQUEST;
        if(status == HttpStatus.UNAUTHORIZED || e.getCode() == 401)
            throw new BadCredentialsException("Invalid token", e);

        return new ResponseEntity<>(exceptionResponse, status);
    }
}
