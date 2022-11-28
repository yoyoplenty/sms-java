package com.example.SchoolManagementSystem.Users.Validator;

import com.example.SchoolManagementSystem.Users.Annotation.UserId;
import com.example.SchoolManagementSystem.Users.User;
import com.example.SchoolManagementSystem.Users.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.UUID;

public class ValidateId implements ConstraintValidator<UserId, UUID> {

    @Autowired
    UserService userService;

    @Override
    public boolean isValid(UUID id, ConstraintValidatorContext constraintValidatorContext) {
        User user = userService.getUserById(id);

        return id != null && user != null;
    }
}
