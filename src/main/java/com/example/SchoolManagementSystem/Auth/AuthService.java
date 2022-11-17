package com.example.SchoolManagementSystem.Auth;


import com.example.SchoolManagementSystem.Auth.Dto.LoginDto;
import com.example.SchoolManagementSystem.Auth.Security.Jwt.JwtUtils;
import com.example.SchoolManagementSystem.Users.Dto.NewUserDto;
import com.example.SchoolManagementSystem.Users.User;
import com.example.SchoolManagementSystem.Users.UserService;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;

import javax.security.auth.login.LoginException;
import java.util.HashMap;
import java.util.Map;

@Controller
public class AuthService {

    @Autowired
    UserService userService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    public User signUp(NewUserDto user) throws UnirestException {
        user.setPassword(encoder.encode(user.getPassword()));

        return userService.createUser(user);
    }

    public Map<String, Object> signIn(LoginDto loginDto) throws LoginException {
        //This here authenticate user and return the principal (user)
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        Map<String, Object> data = new HashMap<String, Object>();
        data.put("accessToken", jwt);
        data.put("user", authentication.getPrincipal());
        data.put("authorities", authentication.getAuthorities());

        return data;
    }

//    public Object confirmEmail(String token) {
//    }
}
