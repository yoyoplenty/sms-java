package com.example.SchoolManagementSystem.Auth;


import com.example.SchoolManagementSystem.Auth.Dto.LoginDto;
import com.example.SchoolManagementSystem.Auth.Security.Jwt.JwtUtils;
import com.example.SchoolManagementSystem.Auth.Security.Service.UserDetailsImpl;
import com.example.SchoolManagementSystem.Enum.EnumEmailContent;
import com.example.SchoolManagementSystem.Users.Dto.NewUserDto;
import com.example.SchoolManagementSystem.Users.User;
import com.example.SchoolManagementSystem.Users.UserService;
import com.example.SchoolManagementSystem.Utils.Email.EmailService;
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
import java.util.UUID;

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

    @Autowired
    EmailService emailService;

    public User signUp(NewUserDto user) throws UnirestException {
        user.setPassword(encoder.encode(user.getPassword()));

        return userService.createUser(user);
    }

    public Map<String, Object> signIn(LoginDto loginDto) throws LoginException {
        //This here authenticate user and return the principal (user)
        Authentication authentication = loginDto.getEmail() == null ?
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getStudentId(), loginDto.getPassword())) :
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));

        UserDetailsImpl user = (UserDetailsImpl) authentication.getPrincipal();

        if (!user.getEnabled() || user.getLocked())
            throw new IllegalStateException("user is inactive or deactivated");

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        Map<String, Object> data = new HashMap<>();
        data.put("accessToken", jwt);
        data.put("user", authentication.getPrincipal());
//        data.put("authorities", authentication.getAuthorities());

        return data;
    }

    public Object confirmEmail(String token) {
        jwtUtils.validateJwtToken(token);
        String decodedToken = jwtUtils.getUserNameFromJwtToken(token);

        User user = userService.findUserByConfirmToken(decodedToken);
        if (user.getEnabled())
            throw new IllegalStateException("user already active");

        return userService.updateStatus(user.getId(), true);
    }

    public Object forgetPassword(String email) throws UnirestException {
        User user = userService.findUserByEmail(email);
        if (!user.getEnabled())
            throw new IllegalStateException("user is inactive, please check your mail and confirm email");

        String resetToken = UUID.randomUUID().toString();
        user.setResetToken(resetToken);

        emailService.sendEmailToUser(user, EnumEmailContent.ForgetPasswordMail);
        return userService.UpdateUser(user, user.getId());
    }

    public Object resetPassword(String token, String password) {
        if (password.isEmpty()) throw new IllegalArgumentException("Password must not be empty");

        String resetToken = jwtUtils.getUserNameFromJwtToken(token);
        User user = userService.findUserByResetToken(resetToken);
        user.setPassword(encoder.encode(password));

        return userService.UpdateUser(user, user.getId());
    }

    public Object resendEmail(String email) throws UnirestException {
        User user = userService.findUserByEmail(email);
        if (user.getEnabled())
            throw new IllegalStateException("user already active");

        emailService.sendEmailToUser(user, EnumEmailContent.RegistrationMail);
        return user;
    }
}
