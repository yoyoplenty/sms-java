package com.example.SchoolManagementSystem.Utils.Validator;

import com.example.SchoolManagementSystem.Enum.EnumGrade;
import com.example.SchoolManagementSystem.Utils.Annotations.GradeEnum;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

public class GradeEnumValidator implements ConstraintValidator<GradeEnum, EnumGrade> {
    private EnumGrade[] subset;

    @Override
    public void initialize(GradeEnum constraint) {
        this.subset = constraint.anyOf();
    }

    @Override
    public boolean isValid(EnumGrade value, ConstraintValidatorContext context) {
        return value == null || Arrays.asList(subset).contains(value);
    }
}
