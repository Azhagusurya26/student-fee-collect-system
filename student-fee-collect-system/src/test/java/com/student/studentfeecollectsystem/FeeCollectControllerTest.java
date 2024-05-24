package com.student.studentfeecollectsystem;

import com.student.studentfeecollectsystem.dtos.*;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.TestMethodOrder;

import org.springframework.http.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(OrderAnnotation.class)
public class FeeCollectControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    @Order(2)
    void shouldReturnStudentForGivenId() {
        ResponseEntity<Student> response = testRestTemplate.getForEntity("/Students/1",Student.class);
        Student student = response.getBody();
        assertEquals(student.getStudentName(),"surya");
    }

    @Test
    @Order(1)
    void createShouldAddEntityOfStudent(){
        StudentRequestDto studentRequestDto = new StudentRequestDto();
        studentRequestDto.setStudentName("testStudent");
        studentRequestDto.setMobileNumber("72592847501");
        studentRequestDto.setSchoolName("AKT");
        studentRequestDto.setSemesterId("I");
        HttpEntity<StudentRequestDto> httpEntity = new HttpEntity<>(studentRequestDto);
        ResponseEntity<String> response = testRestTemplate.exchange("/Students/", HttpMethod.POST, httpEntity, String.class);
        assertEquals(response.getStatusCode(), HttpStatus.CREATED);
    }

    @Test
    @Order(1)
    void shouldCreateReceiptWhenCollectingFee(){
        FeeRequestDto feeRequestDto = new FeeRequestDto();
        feeRequestDto.setFeeAmount(30000.0);
        feeRequestDto.setSemesterId("I");
        feeRequestDto.setStudentID(2l);
        HttpEntity<FeeRequestDto> httpEntity = new HttpEntity<>(feeRequestDto);
        ResponseEntity<ReceiptResponseDto> response = testRestTemplate.exchange("/v1/collectFee", HttpMethod.POST, httpEntity, ReceiptResponseDto.class);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }


    @Test
    @Order(3)
    void updateShouldUpdateStudentDetails(){
        StudentUpdateRequestDto studentUpdateRequestDto = new StudentUpdateRequestDto();
        studentUpdateRequestDto.setStudentName("testuser");
        studentUpdateRequestDto.setId(1l);
        HttpEntity<StudentUpdateRequestDto> httpEntity = new HttpEntity<>(studentUpdateRequestDto, new HttpHeaders());
        ResponseEntity<String> response = testRestTemplate.exchange("/Students/1", HttpMethod.PUT, httpEntity, String.class);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Order(4)
    @Test
    void shouldDeleteStudentDetails(){
        ResponseEntity<String> response = testRestTemplate.exchange("/Students/1", HttpMethod.DELETE, null, String.class);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

}
