package com.example.student_service.service;

import com.example.student_service.dto.ApiResponse;
import com.example.student_service.dto.DepartmentDTO;
import com.example.student_service.dto.StudentDTO;
import com.example.student_service.dto.StudentDetailsDTO;
import com.example.student_service.exception.StudentAlreadyExistsException;
import com.example.student_service.exception.StudentNotFoundException;
import com.example.student_service.feign.DepartmentClient;
import com.example.student_service.model.Student;
import com.example.student_service.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private DepartmentClient departmentClient;

    public ApiResponse<List<Student>> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        if(students.isEmpty()){
            throw new StudentNotFoundException("No students found");
        }
        ApiResponse<List<Student>> apiResponse = new ApiResponse<>(HttpStatus.OK.value(),"Students fetched Successfully",students);

        return apiResponse;
    }

    public ApiResponse<Student> getStudentByID(Integer id) {
        Student department= studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("Student not found with ID: " + id));

        ApiResponse apiResponse = new ApiResponse(HttpStatus.OK.value(), "Student Fetched Successfully",department);
        return apiResponse;
    }

    public ApiResponse<Student> createStudent(StudentDTO studentDTO) {

        Integer departmentId = studentDTO.getDepartmentId();
        departmentClient.getDepartmentByID(departmentId);

        if(studentRepository.existsByStudentName(studentDTO.getStudentName())){
            throw new StudentAlreadyExistsException("Student name already Exists");
        }

        Student student = new Student();
        student.setStudentName(studentDTO.getStudentName());
        student.setEmail(studentDTO.getEmail());
        student.setDepartmentId(studentDTO.getDepartmentId());


        Student savedStudent = studentRepository.save(student);
        return new ApiResponse<>(HttpStatus.CREATED.value(), "Student created successfully", savedStudent);
    }

    // get student with department details - microservice communication
    public ApiResponse<StudentDetailsDTO> getStudentDetails(Integer id) {
        Student student = studentRepository.findById(id).orElseThrow(()-> new StudentNotFoundException("Student not found with this ID: "+id));

        StudentDetailsDTO details = new StudentDetailsDTO();
        details.setStudentId(student.getStudentId());
        details.setStudentName(student.getStudentName());
        details.setEmail(student.getEmail());

        Integer departmentId = student.getDepartmentId();
        ResponseEntity<ApiResponse<DepartmentDTO>> response = departmentClient.getDepartmentByID(departmentId);

        DepartmentDTO department = response.getBody().getData();
        details.setDepartment(department);

        return new ApiResponse<>(HttpStatus.OK.value(),"Student Details fetched Successfully",details);

    }
}
