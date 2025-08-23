package com.example.yoda.Chapter21.dto;
import com.example.yoda.Chapter21.annotations.EmployeeRoleValidation;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class EmployeeDto {
    private Long id;


    @NotBlank(message = "Required field in Employee: Name") //used for strings so that they are not black
    @Size(min=3,max=20,message = "Length of Name should be in range of [3,20]")// Comes from validation constraints . Still need to tell in controller that we want to valuidate
    private String name;

    @NotBlank(message = "Email cannot be Null")
    @Email(message = "Email should be a valid Email") //checks for valid email
    private String email;

    // @max -> puts an upper limit of max @min vice versa
    @NotBlank
    //@Pattern(regexp = "^(ADMIN|USER)$", message = "role Can either be USER Or Admin")
    @EmployeeRoleValidation // no need to define message since its already in annotation class
    private String role; //CAN either be ADMIN OR USER

    @JsonProperty("active")
    @AssertTrue(message = "Employee should be active")
    private boolean active;

    @Positive(message = "Salary cannot be negative or zero")
    @NotNull(message = "Salary cannot be null")
    @Digits(integer = 7, fraction = 3, message = "Must be 7 digits with 3 decimals max")
    private Double salary;


}
