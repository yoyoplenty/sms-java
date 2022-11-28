package com.example.SchoolManagementSystem.Users.Validator;

import com.example.SchoolManagementSystem.Users.Annotation.EmailPresent;
import com.example.SchoolManagementSystem.Users.User;
import com.example.SchoolManagementSystem.Users.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidateUserEmail implements ConstraintValidator<EmailPresent, String> {

    @Autowired
    UserService userService;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        User user = userService.getUserByEmail(value);

        return value != null && user == null;
    }
}
