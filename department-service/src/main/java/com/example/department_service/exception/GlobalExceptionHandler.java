package com.example.department_service.exception;

import com.example.department_service.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DepartmentNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleDepartmentNotFoundException(DepartmentNotFoundException e){
        return new ResponseEntity(new ErrorResponse(e.getMessage(),HttpStatus.NOT_FOUND.value()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DepartmentAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleDepartmentAlreadyExistsException(DepartmentAlreadyExistsException e){
        return new ResponseEntity(new ErrorResponse(e.getMessage(),HttpStatus.CONFLICT.value()), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleStudentNotFoundException(Exception e){
        return new ResponseEntity<>(new ErrorResponse("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR.value()),HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
