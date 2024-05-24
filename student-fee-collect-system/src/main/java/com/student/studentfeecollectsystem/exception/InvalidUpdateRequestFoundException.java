package com.student.studentfeecollectsystem.exception;

import org.springframework.http.HttpStatus;

public class InvalidUpdateRequestFoundException extends BaseException {
    public InvalidUpdateRequestFoundException(HttpStatus httpStatus, String message) {
        super(httpStatus, message);

    }
}
