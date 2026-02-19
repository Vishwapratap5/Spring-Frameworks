package com.guru.springsecurity_learning.Exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ErrorResponse {

    private LocalDateTime timestamp;
    private String errorMessage;
    private String errorDetails;
    private HttpStatus errorCode;
}
