package com.example.yoda.Chapter21.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class EmployeeDto {
    private Long id;
    @NotNull(message = "Name Required")  // Comes from validation constraints . Still need to tell in controller that we want to valuidate
    private String name;
    private String email;

}
