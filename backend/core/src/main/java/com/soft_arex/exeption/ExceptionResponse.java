package com.soft_arex.exeption;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
@AllArgsConstructor
@Data
public class ExceptionResponse {
    private HttpStatus status;
    private Integer statusInt;
    private String message;
    private int code;

    public ExceptionResponse() {
    }

    public ExceptionResponse(String message, HttpStatus status, int code) {
        this.message = message;
        this.status = status;
        this.code = code;
    }


}
