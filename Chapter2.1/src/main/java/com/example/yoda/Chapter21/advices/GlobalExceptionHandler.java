package com.example.yoda.Chapter21.advices;

import com.example.yoda.Chapter21.exceptions.ResourceNotFoundException;
import org.apache.catalina.core.AprStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RestControllerAdvice //applies to all controller
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class) //handles all the NoSuchElementException
    public ResponseEntity<ApiResponse<?>> handleResourceNotFound(ResourceNotFoundException exception){
        //using builder() we can set everything inside the ApiError class and using build() we are creating a new instance
        //build() return a new instance
        ApiError apiError = ApiError
                .builder()
                .status(HttpStatus.NOT_FOUND)
                .message(exception.getMessage())
                .build();
        //we are not returning a new ResponseEntity with ApiError object and status code
        //we can now return an object with meaningful data
        return buildErrorResponseEntity(apiError);

    }




    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<?>> handleInternalServerError(Exception exception){
        ApiError apiError = ApiError
                .builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .message(exception.getMessage())
                .build();
        return buildErrorResponseEntity(apiError);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<?>> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception){
        List<String> errors = exception
                .getBindingResult()
                .getAllErrors()
                .stream()
                .map(error->error.getDefaultMessage())
                .collect(Collectors.toList());
        ApiError apiError = ApiError.builder()
                .status(HttpStatus.BAD_REQUEST)
                .message("Input Validation Failed")
                .subErrors(errors)
                .build();
        return buildErrorResponseEntity(apiError);
    }
    private ResponseEntity<ApiResponse<?>> buildErrorResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(new ApiResponse<>(apiError),apiError.getStatus());
    }


}
