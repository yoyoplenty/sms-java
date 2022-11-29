package com.example.SchoolManagementSystem.Student.Validator;

import com.example.SchoolManagementSystem.Student.Annotation.StudentPresent;
import com.example.SchoolManagementSystem.Student.Dto.NewStudentDto;
import com.example.SchoolManagementSystem.Student.Student;
import com.example.SchoolManagementSystem.Student.StudentService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidateStudentNames implements ConstraintValidator<StudentPresent, NewStudentDto> {

    @Autowired
    StudentService studentService;

    @Override
    public boolean isValid(NewStudentDto newStudentDto,
                           ConstraintValidatorContext constraintValidatorContext) {

        Student student = studentService.findStudentByNames(newStudentDto.getFirstName(),
                newStudentDto.getLastName(), newStudentDto.getMiddleName());

        return student == null;
    }
}
