package com.example.department_service.service;


import com.example.department_service.dto.ApiResponse;
import com.example.department_service.dto.DepartmentDTO;
import com.example.department_service.exception.DepartmentAlreadyExistsException;
import com.example.department_service.exception.DepartmentNotFoundException;
import com.example.department_service.model.Department;
import com.example.department_service.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;


    public ApiResponse<List<Department>> getAllDepartments() {
        List<Department> departments = departmentRepository.findAll();
        if(departments.isEmpty()){
            throw new DepartmentNotFoundException("No departments found");
        }
        ApiResponse<List<Department>> apiResponse = new ApiResponse<>(HttpStatus.OK.value(),"Departments fetched Successfully",departments);

        return apiResponse;
    }

    public ApiResponse<Department> getDepartmentByID(Integer id) {
        Department department= departmentRepository.findById(id)
                .orElseThrow(() -> new DepartmentNotFoundException("Department not found with ID: " + id));

        ApiResponse apiResponse = new ApiResponse(HttpStatus.OK.value(), "Department Fetched Successfully",department);
        return apiResponse;
    }

    public ApiResponse<Department> createDepartment(DepartmentDTO departmentDTO) {
        if(departmentRepository.existsByDepartmentName(departmentDTO.getDepartmentName())){
            throw new DepartmentAlreadyExistsException("Department name already Exists");
        }

        Department department = new Department();
        department.setDepartmentName(departmentDTO.getDepartmentName());
        department.setHOD(departmentDTO.getHOD());
        department.setLocation(departmentDTO.getLocation());
        department.setNbaAccredited(departmentDTO.isNbaAccredited());

        Department savedDepartment = departmentRepository.save(department);
        return new ApiResponse<>(HttpStatus.CREATED.value(), "Department created successfully", savedDepartment);
    }
}
