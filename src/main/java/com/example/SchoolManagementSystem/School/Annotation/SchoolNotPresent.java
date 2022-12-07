package com.example.SchoolManagementSystem.School.Annotation;

import com.example.SchoolManagementSystem.School.Validator.ValidateSchoolId;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = ValidateSchoolId.class)
public @interface SchoolNotPresent {
    String message() default "school does not exist";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
