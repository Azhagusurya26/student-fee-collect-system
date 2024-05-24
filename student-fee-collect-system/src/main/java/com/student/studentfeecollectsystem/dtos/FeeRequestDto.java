package com.student.studentfeecollectsystem.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class FeeRequestDto {

    private Long studentID;

    private Double feeAmount;

    private String semesterId;

    public Long getStudentID() {
        return studentID;
    }

    public void setStudentID(Long studentID) {
        this.studentID = studentID;
    }

    public Double getFeeAmount() {
        return feeAmount;
    }

    public void setFeeAmount(Double feeAmount) {
        this.feeAmount = feeAmount;
    }

    public String getSemesterId() {
        return semesterId;
    }

    public void setSemesterId(String semesterId) {
        this.semesterId = semesterId;
    }

    @Override
    public String toString() {
        return "FeeRequestDto{" +
                "studentID=" + studentID +
                ", feeAmount=" + feeAmount +
                ", semesterId='" + semesterId + '\'' +
                '}';
    }
}
