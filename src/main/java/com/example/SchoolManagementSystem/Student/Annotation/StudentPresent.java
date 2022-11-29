package com.example.SchoolManagementSystem.Student.Annotation;


import com.example.SchoolManagementSystem.Student.Validator.ValidateStudentNames;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.ANNOTATION_TYPE,
        ElementType.TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = ValidateStudentNames.class)
public @interface StudentPresent {
    String message() default "student with provided names already present";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
