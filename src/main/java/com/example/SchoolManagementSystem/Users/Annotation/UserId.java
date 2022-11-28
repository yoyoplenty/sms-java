package com.example.SchoolManagementSystem.Users.Annotation;

import com.example.SchoolManagementSystem.Users.Validator.ValidateId;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;


@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = ValidateId.class)
public @interface UserId {
    String message() default "user does not exist";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}




