package com.student.studentfeecollectsystem.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.student.studentfeecollectsystem.dtos.Semester;
import com.student.studentfeecollectsystem.dtos.Student;
import com.student.studentfeecollectsystem.dtos.StudentRequestDto;
import com.student.studentfeecollectsystem.dtos.StudentUpdateRequestDto;
import com.student.studentfeecollectsystem.exception.InvalidUpdateRequestFoundException;
import com.student.studentfeecollectsystem.exception.SemesterNotFoundException;
import com.student.studentfeecollectsystem.exception.StudentNotFoundException;
import com.student.studentfeecollectsystem.repository.SemesterRepository;
import com.student.studentfeecollectsystem.repository.StudentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.student.studentfeecollectsystem.enums.StudentErrorConstants.STUDENT_NOT_FOUND;

@Service
public class StudentServiceImpl implements StudentService {

    private StudentsRepository studentsRepository;

    private SemesterRepository semesterRepository;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    StudentServiceImpl(StudentsRepository studentsRepository, SemesterRepository semesterRepository) {
        this.studentsRepository = studentsRepository;
        this.semesterRepository = semesterRepository;
    }

    @Override
    public ResponseEntity<String> createStudent(StudentRequestDto studentRequestDto) throws SemesterNotFoundException {
        Student student = objectMapper.convertValue(studentRequestDto, Student.class);
        Optional<Semester> semester = semesterRepository.findById(studentRequestDto.getSemesterId());
        if(semester.isEmpty()){
            throw new SemesterNotFoundException(HttpStatus.BAD_REQUEST, "Invalid semester Id in the request");
        }
        student.setSemester(semester.get());
        studentsRepository.save(student);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Student> getStudentDetails(String studentId) throws StudentNotFoundException {
        Optional<Student> student = studentsRepository.findById(Long.valueOf(studentId));
        if (student.isPresent()) {
            return ResponseEntity.ok(student.get());
        }
        throw new StudentNotFoundException(STUDENT_NOT_FOUND.getHttpStatus(), STUDENT_NOT_FOUND.getMessage());
    }

    @Override
    public Page<Student> getAllStudentDetails(Pageable pageable) {
        return studentsRepository.findAll(pageable);
    }

    @Override
    public ResponseEntity<String> updateStudent(Long studentID, StudentUpdateRequestDto studentUpdateRequestDto) throws StudentNotFoundException, InvalidUpdateRequestFoundException {
        if (studentID.compareTo(studentUpdateRequestDto.getId()) == 0) {
            Optional<Student> student = studentsRepository.findById(studentID);
            if (student.isEmpty()) {
                throw new StudentNotFoundException(STUDENT_NOT_FOUND.getHttpStatus(), STUDENT_NOT_FOUND.getMessage());
            }
            Student updateStudent = student.get();
            if (studentUpdateRequestDto.getStudentName() != null) {
                updateStudent.setStudentName(studentUpdateRequestDto.getStudentName());
            }
            if (studentUpdateRequestDto.getMobileNumber() != null) {
                updateStudent.setMobileNumber(studentUpdateRequestDto.getMobileNumber());
            }
            studentsRepository.save(updateStudent);
        }
        throw new InvalidUpdateRequestFoundException(HttpStatus.BAD_REQUEST, "mismatch in the student id");
    }


    @Override
    public void deleteStudentDetails(Long studentID) throws StudentNotFoundException {
        if (!studentsRepository.existsById(studentID)) {
            throw new StudentNotFoundException(STUDENT_NOT_FOUND.getHttpStatus(), STUDENT_NOT_FOUND.getMessage());
        }
        studentsRepository.deleteById(studentID);
    }

}
