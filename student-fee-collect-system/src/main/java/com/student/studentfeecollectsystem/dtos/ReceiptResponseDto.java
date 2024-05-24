package com.student.studentfeecollectsystem.dtos;


import java.time.LocalDate;

public class ReceiptResponseDto {

    private Long studentID;

    private String studentName;

    private String semester;

    private Double feeAmount;

    private Long receiptID;

    private LocalDate paymentDoneOn;


    public Long getReceiptID() {
        return receiptID;
    }

    public void setReceiptID(Long receiptID) {
        this.receiptID = receiptID;
    }

    public LocalDate getPaymentDoneOn() {
        return paymentDoneOn;
    }

    public void setPaymentDoneOn(LocalDate paymentDoneOn) {
        this.paymentDoneOn = paymentDoneOn;
    }

    public Long getStudentID() {
        return studentID;
    }

    public void setStudentID(Long studentID) {
        this.studentID = studentID;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Double getFeeAmount() {
        return feeAmount;
    }

    public void setFeeAmount(Double feeAmount) {
        this.feeAmount = feeAmount;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }
}
