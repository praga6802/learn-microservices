package com.example.student_service.controller;

import com.example.student_service.dto.ApiResponse;
import com.example.student_service.dto.StudentDTO;
import com.example.student_service.dto.StudentDetailsDTO;
import com.example.student_service.model.Student;
import com.example.student_service.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;


    @GetMapping
    public ResponseEntity<ApiResponse<List<Student>>> getAllStudents(){
        return new ResponseEntity<>(studentService.getAllStudents(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Student>> getStudentByID(@PathVariable Integer id){
        return new ResponseEntity<>(studentService.getStudentByID(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Student>> createStudent(@RequestBody StudentDTO studentDTO){
        return new ResponseEntity<>(studentService.createStudent(studentDTO),HttpStatus.CREATED);
    }

    // get student with department details
    @GetMapping("/details/{id}")
    public ResponseEntity<ApiResponse<StudentDetailsDTO>> getStudentDetails(@PathVariable Integer id){
        return new ResponseEntity<>(studentService.getStudentDetails(id), HttpStatus.OK);
    }
}
