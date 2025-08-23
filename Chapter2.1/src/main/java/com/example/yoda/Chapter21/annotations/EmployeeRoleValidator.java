package com.example.yoda.Chapter21.annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;

//This is the validator for validation
public class EmployeeRoleValidator implements ConstraintValidator<EmployeeRoleValidation,String>  {//<Where , What>
    @Override //overriding tht isValid function and giving our own logic
    public boolean isValid(String inputRole, ConstraintValidatorContext constraintValidatorContext) {
        List<String> roles = List.of("USER","ADMIN"); //we can add multiple roles here
         return roles.contains(inputRole);
    }
}
