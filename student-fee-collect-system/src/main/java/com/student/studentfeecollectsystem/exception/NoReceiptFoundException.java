package com.student.studentfeecollectsystem.exception;

import org.springframework.http.HttpStatus;

public class NoReceiptFoundException extends BaseException {
    public NoReceiptFoundException(HttpStatus httpStatus, String message) {
        super(httpStatus, message);
    }
}
