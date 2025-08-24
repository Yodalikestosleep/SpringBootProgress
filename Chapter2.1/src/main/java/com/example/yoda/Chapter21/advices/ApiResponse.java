package com.example.yoda.Chapter21.advices;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class ApiResponse<T> {
    private T data;
    @JsonFormat(pattern = "hh:mm:ss dd-MM-yyyy")
    private LocalDateTime timestamp;
    private ApiError error;

    public ApiResponse(){
        this.timestamp = LocalDateTime.now();
    }
    public ApiResponse(T data){
        this();
        this.data = data;

    }
    public ApiResponse(ApiError error){
        this();
        this.error = error;
    }

}
