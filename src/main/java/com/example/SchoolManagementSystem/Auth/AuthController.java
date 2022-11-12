package com.example.SchoolManagementSystem.Auth;


import com.example.SchoolManagementSystem.Auth.Dto.LoginDto;
import com.example.SchoolManagementSystem.Auth.Security.Jwt.JwtUtils;
import com.example.SchoolManagementSystem.Responses.ResponseHandler;
import com.example.SchoolManagementSystem.Users.Dto.NewUserDto;
import com.example.SchoolManagementSystem.Users.User;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("api/v1/auth")
@AllArgsConstructor
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    AuthService authService;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/signup")
    public ResponseEntity<Object> SignUp(@Valid @RequestBody NewUserDto user) {
        try {
            User newUser = authService.signUp(user);
            return ResponseHandler.generateResponse("Successfully created user!", HttpStatus.OK, newUser);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }

    @PostMapping("/signin")
    public ResponseEntity<Object> SignIn(@Valid @RequestBody LoginDto loginDto) {
        try {
            String jwt = authService.signIn(loginDto);

            return ResponseHandler.generateResponse("Successfully created user!", HttpStatus.OK, jwt);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }
}
