package com.example.SchoolManagementSystem.Auth;

import com.example.SchoolManagementSystem.Auth.Dto.ForgetPasswordDto;
import com.example.SchoolManagementSystem.Auth.Dto.LoginDto;
import com.example.SchoolManagementSystem.Users.Dto.NewUserDto;
import com.example.SchoolManagementSystem.Users.User;
import com.example.SchoolManagementSystem.Utils.Handlers.Responses.ResponseHandler;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/v1/auth")
@AllArgsConstructor
public class AuthController {
    @Autowired
    AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<Object> SignUp(@Valid @RequestBody NewUserDto user) {
        try {
            User newUser = authService.signUp(user);
            return ResponseHandler.generateResponse("Successfully created user, please check your mail!", HttpStatus.OK, newUser);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY, e.getMessage());
        }
    }

    @PostMapping("/signin")
    public ResponseEntity<Object> SignIn(@Valid @RequestBody LoginDto loginDto) {
        try {
            Object data = authService.signIn(loginDto);

            return ResponseHandler.generateResponse("Successfully signed in!", HttpStatus.OK, data);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY, e.getMessage());
        }
    }

    @GetMapping("confirm_email/{token}")
    public ResponseEntity<Object> confirmEmail(@PathVariable String token) {
        try {
            Object data = authService.confirmEmail(token);

            return ResponseHandler.generateResponse("Successfully confirmed email!", HttpStatus.OK, data);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY, e.getMessage());
        }
    }

    @PostMapping("forget_password")
    public ResponseEntity<Object> forgetPassword(@RequestBody @Valid ForgetPasswordDto forgetPasswordDto) {
        try {
            Object data = authService.forgetPassword(forgetPasswordDto.getEmail());

            return ResponseHandler.generateResponse("forget password email sent!", HttpStatus.OK, data);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY, e.getMessage());
        }
    }

    @PatchMapping("reset_password/{token}")
    public ResponseEntity<Object> resetPassword(@PathVariable String token, @RequestBody @Valid ForgetPasswordDto forgetPasswordDto) {
        try {
            Object data = authService.resetPassword(token, forgetPasswordDto.getPassword());

            return ResponseHandler.generateResponse("password reset successful", HttpStatus.OK, data);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY, e.getMessage());
        }
    }

    @PostMapping("resend_email/{email}")
    public ResponseEntity<Object> resendEmail(@PathVariable String email) {
        try {
            Object data = authService.resendEmail(email);

            return ResponseHandler.generateResponse("email successfully sent!", HttpStatus.OK, data);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY, e.getMessage());
        }
    }

}
