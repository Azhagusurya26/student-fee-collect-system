package com.student.studentfeecollectsystem;

import com.student.studentfeecollectsystem.dtos.Student;
import com.student.studentfeecollectsystem.dtos.StudentRequestDto;
import com.student.studentfeecollectsystem.dtos.StudentUpdateRequestDto;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.TestMethodOrder;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(OrderAnnotation.class)
public class FeeCollectControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    @Order(2)
    void shouldReturnStudentForGivenId() {
        ResponseEntity<Student> response = testRestTemplate.getForEntity("/Students/8",Student.class);
        Student student = response.getBody();
        assertEquals(student.getStudentName(),"surya3");
    }

    @Test
    @Order(1)
    void createShouldAddEntityOfStudent(){
        StudentRequestDto studentRequestDto = new StudentRequestDto();
        studentRequestDto.setStudentName("testStudent");
        studentRequestDto.setMobileNumber("72592847501");
        studentRequestDto.setSchoolName("AKT");
        studentRequestDto.setSemesterId("I");
        studentRequestDto.setStudentID(8L);
        HttpEntity<StudentRequestDto> httpEntity = new HttpEntity<>(studentRequestDto);
        ResponseEntity<String> response = testRestTemplate.exchange("/Students/", HttpMethod.POST, httpEntity, String.class);
        assertEquals(response.getStatusCode(), HttpStatus.CREATED);
    }


    @Test
    @Order(3)
    void updateShouldUpdateStudentDetails(String input){
        StudentUpdateRequestDto studentUpdateRequestDto = new StudentUpdateRequestDto();
        studentUpdateRequestDto.setStudentName(input);
        studentUpdateRequestDto.setId(8l);
        HttpEntity<StudentUpdateRequestDto> httpEntity = new HttpEntity<>(new StudentUpdateRequestDto());
        ResponseEntity<String> response = testRestTemplate.exchange("/Students/8", HttpMethod.PUT, httpEntity, String.class);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Order(4)
    @Test
    void shouldDeleteStudentDetails(){
        ResponseEntity<String> response = testRestTemplate.exchange("/Students/8", HttpMethod.DELETE, null, String.class);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

}
