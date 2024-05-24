package com.student.studentfeecollectsystem.exception;

import org.springframework.http.HttpStatus;

public class SemesterNotFoundException extends BaseException {
    public SemesterNotFoundException(HttpStatus httpStatus, String message) {
        super(httpStatus, message);
    }
}
