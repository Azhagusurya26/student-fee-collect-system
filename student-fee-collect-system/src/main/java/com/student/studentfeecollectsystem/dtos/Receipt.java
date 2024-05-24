package com.student.studentfeecollectsystem.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@JsonIgnoreProperties("student")
public class Receipt {


    @Id
    @Column(name = "receipt_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long receiptId;

    @Column(name = "payment_done_on")
    private LocalDate paymentDoneOn;

    @ManyToOne(targetEntity=Student.class, fetch = FetchType.LAZY)
    private Student student;

    public Long getReceiptId() {
        return receiptId;
    }

    public void setReceiptId(Long receiptId) {
        this.receiptId = receiptId;
    }

    public LocalDate getPaymentDoneOn() {
        return paymentDoneOn;
    }

    public void setPaymentDoneOn(LocalDate paymentDoneOn) {
        this.paymentDoneOn = paymentDoneOn;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
