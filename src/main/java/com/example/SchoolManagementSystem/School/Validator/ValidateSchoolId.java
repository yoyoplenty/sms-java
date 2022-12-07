package com.example.SchoolManagementSystem.School.Validator;

import com.example.SchoolManagementSystem.School.Annotation.SchoolNotPresent;
import com.example.SchoolManagementSystem.School.School;
import com.example.SchoolManagementSystem.School.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.UUID;

public class ValidateSchoolId implements ConstraintValidator<SchoolNotPresent, UUID> {
    @Autowired
    SchoolService schoolService;

    @Override
    public boolean isValid(UUID value, ConstraintValidatorContext constraintValidatorContext) {
        School school = schoolService.getSchoolById(value);

        return school != null;
    }
}
