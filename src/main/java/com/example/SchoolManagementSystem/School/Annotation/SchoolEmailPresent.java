package com.example.SchoolManagementSystem.School.Annotation;

import com.example.SchoolManagementSystem.School.Validator.ValidateSchoolEmail;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = ValidateSchoolEmail.class)
public @interface SchoolEmailPresent {
    String message() default "school already exist";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
