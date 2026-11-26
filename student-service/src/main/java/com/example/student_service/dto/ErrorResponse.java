package com.example.student_service.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ErrorResponse {

    private LocalDateTime timestamp;
    private int status;
    private String message;


    public ErrorResponse(String message, int status){
        this.message=message;
        this.status=status;
        this.timestamp=LocalDateTime.now();
    }

}
