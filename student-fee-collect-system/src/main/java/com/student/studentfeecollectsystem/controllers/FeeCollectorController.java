package com.student.studentfeecollectsystem.controllers;

import com.student.studentfeecollectsystem.dtos.FeeRequestDto;
import com.student.studentfeecollectsystem.dtos.ReceiptResponseDto;
import com.student.studentfeecollectsystem.exception.InvalidFeeRequestDetailsFound;
import com.student.studentfeecollectsystem.exception.NoReceiptFoundException;
import com.student.studentfeecollectsystem.exception.StudentNotFoundException;
import com.student.studentfeecollectsystem.service.FeeCollectorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FeeCollectorController {

    private final FeeCollectorService feeCollectorService;

    @Autowired
    FeeCollectorController(FeeCollectorService feeCollectorService){
        this.feeCollectorService = feeCollectorService;
    }

    @Operation(summary = "create a receipt with fee details for studentID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "create reciept details for the given student id",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Page.class))}),
            @ApiResponse(responseCode = "404",
                    description = "No Student details found for the Id",
                    content = @Content)})
    @PostMapping("/collectFee")
    public ResponseEntity<ReceiptResponseDto> collectFee(FeeRequestDto feeRequestDto) throws InvalidFeeRequestDetailsFound {
        return feeCollectorService.collectFee(feeRequestDto);
    }

    @Operation(summary = "fetch receipt details for the receipt ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "fetch student details for the given receipt id",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Page.class))}),
            @ApiResponse(responseCode = "404",
                    description = "No Student details found for the receipt Id",
                    content = @Content)})
    @GetMapping("/Receipts/{receiptId}")
    public ResponseEntity<ReceiptResponseDto> getReceiptDetails(@PathVariable("receiptId") Long receiptId) throws InvalidFeeRequestDetailsFound, NoReceiptFoundException {
        return feeCollectorService.getReceiptById(receiptId);
    }

    @Operation(summary = "fetch receipt details for the student ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "fetch student details for the given student id",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Page.class))}),
            @ApiResponse(responseCode = "404",
                    description = "No Student details found for the student Id",
                    content = @Content)})
    @GetMapping("/Receipts/{studentId}")
    public ResponseEntity<List<ReceiptResponseDto>> getReceiptDetailsForStudent(@PathVariable("studentId") Long studentId) throws InvalidFeeRequestDetailsFound, StudentNotFoundException, NoReceiptFoundException {
        return feeCollectorService.getReceiptsByStudentId(studentId);
    }
}
