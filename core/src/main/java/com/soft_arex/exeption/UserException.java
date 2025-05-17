package com.soft_arex.exeption;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class UserException extends RuntimeException{

    private Long id;
    private int code;
    private HttpStatus status;

    public UserException(Long id) {
        super();
        this.id = id;
    }

    public UserException(Long id,int code) {
        super();
        this.id = id;
        this.code = code;
    }
    public UserException(String message) {
        super(message);
    }
    public UserException(String message, int code) {
        super(message);
        this.code = code;
    }

    public UserException(String message, HttpStatus status) {
        super(message);
        this.status = status;
        this.code = status.value();
    }
    public UserException(String message, HttpStatus status, Long id) {
        super(message);
        this.status = status;
        this.id = id;
    }
}
