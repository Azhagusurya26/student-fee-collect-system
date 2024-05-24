package com.student.studentfeecollectsystem.repository;

import com.student.studentfeecollectsystem.dtos.Semester;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SemesterRepository extends JpaRepository<Semester, String> {
}
