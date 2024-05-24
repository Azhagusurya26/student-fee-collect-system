package com.student.studentfeecollectsystem.exception;

import org.springframework.http.HttpStatus;

public class InvalidFeeRequestDetailsFound extends BaseException {

    public InvalidFeeRequestDetailsFound(HttpStatus httpStatus, String message) {
        super(httpStatus, message);
    }
}
