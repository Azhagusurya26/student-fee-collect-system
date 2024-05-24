package com.student.studentfeecollectsystem.controllers;

import com.student.studentfeecollectsystem.dtos.Student;
import com.student.studentfeecollectsystem.dtos.StudentRequestDto;
import com.student.studentfeecollectsystem.dtos.StudentUpdateRequestDto;
import com.student.studentfeecollectsystem.exception.InvalidUpdateRequestFoundException;
import com.student.studentfeecollectsystem.exception.SemesterNotFoundException;
import com.student.studentfeecollectsystem.exception.StudentNotFoundException;
import com.student.studentfeecollectsystem.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("Students")
public class StudentControllers {

    private final StudentService studentService;

    @Autowired
    StudentControllers(StudentService studentService) {
        this.studentService = studentService;
    }

    @Operation(summary = "create Student")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "created student")})
    @PostMapping("/")
    public ResponseEntity<String> createStudent(@RequestBody StudentRequestDto studentRequestDto) throws SemesterNotFoundException {
        return studentService.createStudent(studentRequestDto);
    }

    @Operation(summary = "create Student")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "created student")})
    @PutMapping("/{studentId}")
    public ResponseEntity<String> updateStudent(@PathVariable Long studentID, @RequestBody StudentUpdateRequestDto studentUpdateRequestDto) throws InvalidUpdateRequestFoundException, StudentNotFoundException {
        return studentService.updateStudent(studentID, studentUpdateRequestDto);
    }

    @Operation(summary = "update Student details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "update student details for given id",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Page.class)) }),
            @ApiResponse(responseCode = "404",
                    description = "No Student details found for the Id",
                    content = @Content) })
    @GetMapping("/{studentId}")
    public ResponseEntity<Student> getStudentDetails(@PathVariable("studentId") String studentID) throws StudentNotFoundException {
        return studentService.getStudentDetails(studentID);
    }
    
    @Operation(summary = "Get All Students")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Student List",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Page.class)) }),
            @ApiResponse(responseCode = "204",
                    description = "No Student details found",
                    content = @Content) })
    @GetMapping("/")
    public Page<Student> getAllStudents(Pageable pageable) {
        return studentService.getAllStudentDetails(pageable);
    }
    @Operation(summary = "delete Student details for ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "delete student details for the given id",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Page.class)) }),
            @ApiResponse(responseCode = "404",
                    description = "No Student details found for the Id",
                    content = @Content) })
    @DeleteMapping("/{studentId}")
    public ResponseEntity<String> deleteStudentDetails(@PathVariable("studentId") Long studentID) throws StudentNotFoundException {
        studentService.deleteStudentDetails(studentID);
        return ResponseEntity.ok().build();
    }
}
