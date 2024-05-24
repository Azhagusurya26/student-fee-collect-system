package com.student.studentfeecollectsystem.dtos;


public class StudentUpdateRequestDto {
    private Long id;

    private String studentName;

    private String mobileNumber;

    private String grade;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "StudentUpdateRequestDto{" +
                "id=" + id +
                ", studentName='" + studentName + '\'' +
                ", mobileNumber='" + mobileNumber + '\'' +
                ", grade='" + grade + '\'' +
                '}';
    }

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

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
}
