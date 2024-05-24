package com.student.studentfeecollectsystem.service;

import com.student.studentfeecollectsystem.dtos.*;
import com.student.studentfeecollectsystem.exception.InvalidFeeRequestDetailsFound;
import com.student.studentfeecollectsystem.exception.NoReceiptFoundException;
import com.student.studentfeecollectsystem.exception.StudentNotFoundException;
import com.student.studentfeecollectsystem.repository.SemesterRepository;
import com.student.studentfeecollectsystem.repository.ReceiptRepository;
import com.student.studentfeecollectsystem.repository.StudentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.student.studentfeecollectsystem.enums.StudentErrorConstants.STUDENT_NOT_FOUND;

@Service
public class FeeCollectServiceImpl implements FeeCollectorService {

    private final ReceiptRepository receiptRepository;

    private final SemesterRepository semesterRepository;

    private final StudentsRepository studentsRepository;

    @Autowired
    FeeCollectServiceImpl(ReceiptRepository receiptRepository, SemesterRepository semesterRepository, StudentsRepository studentsRepository) {
        this.receiptRepository = receiptRepository;
        this.semesterRepository = semesterRepository;
        this.studentsRepository = studentsRepository;
    }

    @Override
    public ResponseEntity<ReceiptResponseDto> collectFee(FeeRequestDto feeRequestDto) throws InvalidFeeRequestDetailsFound {
        Optional<Student> student = studentsRepository.findById(feeRequestDto.getStudentID());
        Optional<Semester> grade = semesterRepository.findById(feeRequestDto.getSemesterId());
        if (student.isPresent() && grade.isPresent() && !isPaid(feeRequestDto) || validateFeeAmount(feeRequestDto, grade.get())) {
            Receipt receipt = new Receipt();
            receipt.setPaymentDoneOn(LocalDate.now());
            receipt.setStudent(student.get());
            Receipt savedReceipt = receiptRepository.save(receipt);
            ReceiptResponseDto receiptResponseDto = buildReceiptResponse(grade.get(), student.get(), savedReceipt);
            return ResponseEntity.ok(receiptResponseDto);
        }
        throw new InvalidFeeRequestDetailsFound(HttpStatus.BAD_REQUEST, "Invalid Fee request details");
    }

    private static ReceiptResponseDto buildReceiptResponse(Semester Semester, Student student, Receipt receipt) {
        ReceiptResponseDto receiptResponseDto = new ReceiptResponseDto();
        receiptResponseDto.setFeeAmount(Semester.getFeeAmount());
        receiptResponseDto.setSemester(student.getSemester().getSemesterId());
        receiptResponseDto.setStudentID(student.getStudentID());
        receiptResponseDto.setStudentName(student.getStudentName());
        receiptResponseDto.setPaymentDoneOn(receipt.getPaymentDoneOn());
        receiptResponseDto.setReceiptID(receipt.getReceiptId());
        return receiptResponseDto;
    }

    private boolean validateFeeAmount(FeeRequestDto feeRequestDto, Semester Semester) {
        return (feeRequestDto.getFeeAmount().compareTo(Semester.getFeeAmount())) >= 0;
    }

    private boolean isPaid(FeeRequestDto feeRequestDto) {
        List<Receipt> receiptList = receiptRepository.findByStudent_studentID(feeRequestDto.getStudentID());
        if (CollectionUtils.isEmpty(receiptList)) {
            return false;
        }
        return receiptList.stream().anyMatch(receipt -> receipt.getStudent().getGrade().equalsIgnoreCase(feeRequestDto.getSemesterId()));
    }

    @Override
    public ResponseEntity<List<ReceiptResponseDto>> getReceiptsByStudentId(Long studentID) throws StudentNotFoundException, NoReceiptFoundException {
        Optional<Student> student = studentsRepository.findById(studentID);
        if (student.isPresent()) {
            List<Receipt> receiptList = student.get().getReceiptList();
            if (receiptList.isEmpty()) {
                throw new NoReceiptFoundException(HttpStatus.NOT_FOUND, "No payment done for the student");
            }

            return ResponseEntity.ok(receiptList.stream().map(receipt ->
                    buildReceiptResponse(student.get().getSemester(), student.get(), receipt)).collect(Collectors.toList()));
        }
        throw new StudentNotFoundException(STUDENT_NOT_FOUND.getHttpStatus(), STUDENT_NOT_FOUND.getMessage());

    }

    @Override
    public ResponseEntity<ReceiptResponseDto> getReceiptById(Long receiptId) throws NoReceiptFoundException {
        Optional<Receipt> receipt = receiptRepository.findById(receiptId);
        if (receipt.isEmpty()) {
            throw new NoReceiptFoundException(HttpStatus.NOT_FOUND, "No receipt found for receipt id");
        }
        Student student = receipt.get().getStudent();
        return ResponseEntity.ok(buildReceiptResponse(student.getSemester(), student, receipt.get()));
    }
}
