package com.example.student_service.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDetailsDTO {
    private Integer studentId;
    private String studentName;
    private String email;
    private DepartmentDTO department;
}
