package com.example.SchoolManagementSystem.School.Validator;

import com.example.SchoolManagementSystem.School.Annotation.SchoolNamePresent;
import com.example.SchoolManagementSystem.School.School;
import com.example.SchoolManagementSystem.School.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidateSchoolName implements ConstraintValidator<SchoolNamePresent, String> {
    @Autowired
    SchoolService schoolService;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        School school = schoolService.findSchoolByName(value);

        return value != null && school == null;
    }
}
