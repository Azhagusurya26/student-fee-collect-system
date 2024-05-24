package com.student.studentfeecollectsystem.repository;


import com.student.studentfeecollectsystem.dtos.Receipt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReceiptRepository extends JpaRepository<Receipt, Long> {

    public List<Receipt> findByStudent_studentID(Long student_id);

}
