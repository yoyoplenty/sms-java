package com.example.SchoolManagementSystem.Utils.Validator;

import com.example.SchoolManagementSystem.Enum.EnumUserType;
import com.example.SchoolManagementSystem.Utils.Annotations.UserTypeEnum;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

public class UserTypeValidator implements ConstraintValidator<UserTypeEnum, EnumUserType> {
    private EnumUserType[] subset;

    @Override
    public void initialize(UserTypeEnum constraint) {
        this.subset = constraint.anyOf();
    }

    @Override
    public boolean isValid(EnumUserType value, ConstraintValidatorContext context) {
        System.out.println(value);
        return value == null || Arrays.asList(subset).contains(value);
    }
}
