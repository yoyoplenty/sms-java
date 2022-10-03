package com.example.SchoolManagementSystem.Auth;


import com.example.SchoolManagementSystem.Responses.ResponseHandler;
import com.example.SchoolManagementSystem.Users.User;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth")
@AllArgsConstructor
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<Object> SignUp(@RequestBody User user) {
        try {
            User newUser = authService.signUp(user);
            return ResponseHandler.generateResponse("Successfully created user!", HttpStatus.OK, newUser);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }


    }
}
