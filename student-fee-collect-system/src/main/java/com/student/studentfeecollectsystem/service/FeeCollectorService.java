package com.student.studentfeecollectsystem.service;

import com.student.studentfeecollectsystem.dtos.FeeRequestDto;
import com.student.studentfeecollectsystem.dtos.ReceiptResponseDto;
import com.student.studentfeecollectsystem.dtos.StudentRequestDto;
import com.student.studentfeecollectsystem.exception.InvalidFeeRequestDetailsFound;
import com.student.studentfeecollectsystem.exception.NoReceiptFoundException;
import com.student.studentfeecollectsystem.exception.StudentNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FeeCollectorService {

    public ResponseEntity<ReceiptResponseDto> collectFee(FeeRequestDto feeRequestDto) throws InvalidFeeRequestDetailsFound;

    public ResponseEntity<List<ReceiptResponseDto>> getReceiptsByStudentId(Long studentID) throws StudentNotFoundException, NoReceiptFoundException;

    public ResponseEntity<ReceiptResponseDto> getReceiptById(Long receiptId) throws NoReceiptFoundException;
}
