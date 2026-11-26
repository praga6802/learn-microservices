package com.example.department_service.exception;

public class DepartmentAlreadyExistsException extends RuntimeException {
    public DepartmentAlreadyExistsException(String message) {
        super(message);
    }
}
