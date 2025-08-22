package com.example.yoda.Chapter21.dto;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class EmployeeDto {
    private Long id;


    @NotBlank(message = "Required field in Employee: Name") //used for strings so that they are not black
    @Size(min=3,max=20,message = "Length of Name should be in range of [3,20]")// Comes from validation constraints . Still need to tell in controller that we want to valuidate
    private String name;

    @Email(message = "Email should be a valid Email") //checks for valid email
    private String email;

    // @max -> puts an upper limit of max @min vice versa

}
