package com.example.SchoolManagementSystem.Auth;


import com.example.SchoolManagementSystem.Users.User;
import com.example.SchoolManagementSystem.Users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class AuthService {

    @Autowired
    UserService userService;

    public User signUp(User user) {
        return userService.createUser(user);
    }
}
