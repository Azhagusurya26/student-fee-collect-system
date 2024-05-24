package com.student.studentfeecollectsystem.exception;

import org.springframework.http.HttpStatus;

public class StudentNotFoundException extends Exception{

    private HttpStatus httpStatus;

    public StudentNotFoundException(HttpStatus httpStatus, String message) {
        super(message);
        this.httpStatus = httpStatus;
    }
}
