package com.example.yoda.Chapter21.annotations;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME) //validate on runtime
@Target({ElementType.FIELD,ElementType.PARAMETER}) //This tells on which types of element to put the validation on
//currently using FIELD and PARAMETER
@Constraint(validatedBy = {EmployeeRoleValidator.class}) //tells the validation login
public @interface EmployeeRoleValidation {

    String message() default "{EmployeeRoleValidator not satisfied}"; //we can write custom message here

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

