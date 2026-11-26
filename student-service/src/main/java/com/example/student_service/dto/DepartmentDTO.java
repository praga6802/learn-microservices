package com.example.student_service.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDTO {
    private Integer departmentId;
    private String departmentName;
    private String HOD;
    private String location;
    private boolean nbaAccredited;
}

