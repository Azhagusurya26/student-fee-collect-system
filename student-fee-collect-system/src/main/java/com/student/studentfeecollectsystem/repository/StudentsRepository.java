package com.student.studentfeecollectsystem.repository;

import com.student.studentfeecollectsystem.dtos.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentsRepository  extends JpaRepository<Student, Long> {

}
