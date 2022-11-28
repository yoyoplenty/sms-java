package com.example.SchoolManagementSystem.Utils.Annotations;

import com.example.SchoolManagementSystem.Enum.EnumUserType;
import com.example.SchoolManagementSystem.Utils.Validator.UserTypeValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE,
        ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = UserTypeValidator.class)
public @interface UserTypeEnum {
    EnumUserType[] anyOf();

    String message() default "must be any of {anyOf}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
