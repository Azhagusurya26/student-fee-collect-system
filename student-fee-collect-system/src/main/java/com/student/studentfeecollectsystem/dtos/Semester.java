package com.student.studentfeecollectsystem.dtos;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Semester {

    @Id
    @Column(name = "semester_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String semesterId;

    @Column(name="fee_amount")
    private Double feeAmount;



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
}
