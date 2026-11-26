package com.example.department_service.controller;

import com.example.department_service.dto.ApiResponse;
import com.example.department_service.dto.DepartmentDTO;
import com.example.department_service.dto.ErrorResponse;
import com.example.department_service.model.Department;
import com.example.department_service.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;


    @GetMapping
    public ResponseEntity<ApiResponse<List<Department>>> getAllDepartments(){
        return new ResponseEntity<>(departmentService.getAllDepartments(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Department>> getDepartmentByID(@PathVariable Integer id){
        return new ResponseEntity<>(departmentService.getDepartmentByID(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Department>> createDepartment(@RequestBody DepartmentDTO departmentDTO){
        return new ResponseEntity<>(departmentService.createDepartment(departmentDTO),HttpStatus.CREATED);
    }

}
