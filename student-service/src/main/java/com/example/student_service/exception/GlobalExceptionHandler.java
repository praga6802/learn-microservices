package com.example.student_service.exception;

import com.example.student_service.dto.ErrorResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(StudentAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleStudentAlreadyExistsException(StudentAlreadyExistsException e){
        return new ResponseEntity<>(new ErrorResponse(e.getMessage(), HttpStatus.CONFLICT.value()),HttpStatus.CONFLICT);
    }

    @ExceptionHandler(StudentNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleStudentNotFoundException(StudentNotFoundException e){
        return new ResponseEntity<>(new ErrorResponse(e.getMessage(), HttpStatus.NOT_FOUND.value()),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleStudentNotFoundException(Exception e){
        return new ResponseEntity<>(new ErrorResponse("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR.value()),HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(FeignException.NotFound.class)
    public ResponseEntity<ErrorResponse> handleFeignNotFoundException(FeignException.NotFound e) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(e.contentUTF8());
        String message = jsonNode.get("message").asString();
        return new ResponseEntity<>(new ErrorResponse(message,e.status()),HttpStatus.NOT_FOUND);
    }
}
