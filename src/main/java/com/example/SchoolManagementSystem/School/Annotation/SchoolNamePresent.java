package com.example.SchoolManagementSystem.School.Annotation;


import com.example.SchoolManagementSystem.School.Validator.ValidateSchoolName;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = ValidateSchoolName.class)
public @interface SchoolNamePresent {
    String message() default "school already exist";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
