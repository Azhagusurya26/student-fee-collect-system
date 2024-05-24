package com.student.studentfeecollectsystem.exception;

import org.springframework.http.HttpStatus;

public class BaseException extends Exception{

    private HttpStatus httpStatus ;

    private  String message;

    BaseException(HttpStatus httpStatus, String message){
        super(message);
        this.httpStatus= httpStatus;
        this.message  = message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
