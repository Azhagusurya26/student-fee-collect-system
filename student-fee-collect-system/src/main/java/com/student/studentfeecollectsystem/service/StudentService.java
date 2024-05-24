package com.student.studentfeecollectsystem.service;

import com.student.studentfeecollectsystem.dtos.Student;
import com.student.studentfeecollectsystem.dtos.StudentRequestDto;
import com.student.studentfeecollectsystem.dtos.StudentUpdateRequestDto;
import com.student.studentfeecollectsystem.exception.InvalidUpdateRequestFoundException;
import com.student.studentfeecollectsystem.exception.SemesterNotFoundException;
import com.student.studentfeecollectsystem.exception.StudentNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface StudentService {

    public ResponseEntity<String> createStudent(StudentRequestDto studentRequestDto) throws SemesterNotFoundException;

    public ResponseEntity<Student> getStudentDetails(String studentId) throws StudentNotFoundException;

    public Page<Student> getAllStudentDetails(Pageable pageable);

    ResponseEntity<String> updateStudent(Long studentID, StudentUpdateRequestDto studentUpdateRequestDto) throws StudentNotFoundException, InvalidUpdateRequestFoundException;

    void deleteStudentDetails(Long studentID) throws StudentNotFoundException;
}
