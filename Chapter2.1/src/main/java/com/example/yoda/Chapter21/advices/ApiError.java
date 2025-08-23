package com.example.yoda.Chapter21.advices;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@Data
@Builder
public class ApiError {
    private HttpStatus status;
    private String message;

}
