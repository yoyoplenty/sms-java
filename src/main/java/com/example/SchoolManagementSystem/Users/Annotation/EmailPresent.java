package com.example.SchoolManagementSystem.Users.Annotation;


import com.example.SchoolManagementSystem.Users.Validator.ValidateUserEmail;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = ValidateUserEmail.class)
public @interface EmailPresent {
    String message() default "user already exist";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
