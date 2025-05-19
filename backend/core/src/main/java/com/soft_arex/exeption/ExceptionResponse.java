package com.soft_arex.exeption;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class ExceptionResponse {
    private HttpStatus status;
    private Integer statusInt;
    private String message;
    private int code;
}
