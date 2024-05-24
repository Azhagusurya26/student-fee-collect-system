package com.student.studentfeecollectsystem.dtos;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {

    @Id
    @GenericGenerator(name="generator", strategy="increment")
    @GeneratedValue(generator="generator")
    @Column(name = "student_id")
    private Long studentID;

    @Column(name="student_name")
    private String studentName;

    @ManyToOne(targetEntity = Semester.class)
    @JoinColumn( name = "semester_id")
    private Semester semester;

    @Column(name = "mobile_number")
    private String mobileNumber;

    @Column(name = "school_name")
    private String schoolName;

    @Column(name = "student_grade")
    private String grade;

    @OneToMany(targetEntity=Receipt.class,cascade = CascadeType.DETACH , fetch = FetchType.LAZY, mappedBy = "student")
    private List<Receipt> receiptList;

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }


    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public Long getStudentID() {
        return studentID;
    }

    public void setStudentID(Long studentID) {
        this.studentID = studentID;
    }

    public List<Receipt> getReceiptList() {
        return receiptList;
    }

    public void setReceiptList(List<Receipt> receiptList) {
        this.receiptList = receiptList;
    }



    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        grade = grade;
    }

    public Semester getSemester() {
        return semester;
    }

    public void setSemester(Semester semester) {
        this.semester = semester;
    }
}
