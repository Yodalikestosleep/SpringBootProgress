package com.example.yoda.Chapter21.advices;

import com.example.yoda.Chapter21.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@RestControllerAdvice //applies to all controller
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class) //handles all the NoSuchElementException
    public ResponseEntity<ApiError> handleResourceNotFound(ResourceNotFoundException exception){
        //using builder() we can set everything inside the ApiError class and using build() we are creating a new instance
        //build() return a new instance
        ApiError apiError = ApiError
                .builder()
                .status(HttpStatus.NOT_FOUND)
                .message(exception.getMessage())
                .build();
        //we are not returning a new ResponseEntity with ApiError object and status code
        //we can now return an object with meaningful data
        return new ResponseEntity<>(apiError,HttpStatus.NOT_FOUND);

    }

}
