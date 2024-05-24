package com.student.studentfeecollectsystem.enums;

import org.springframework.http.HttpStatus;

public enum StudentErrorConstants {

    STUDENT_NOT_FOUND(HttpStatus.NOT_FOUND,  "StudentNotFoundException");

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
    private HttpStatus httpStatus;

    private String message;

    StudentErrorConstants(HttpStatus httpStatus, String message){
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
