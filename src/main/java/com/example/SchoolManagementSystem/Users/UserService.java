package com.example.SchoolManagementSystem.Users;

import com.example.SchoolManagementSystem.Auth.Security.Service.UserDetailsImpl;
import com.example.SchoolManagementSystem.Enum.EnumEmailContent;
import com.example.SchoolManagementSystem.Role.RoleService;
import com.example.SchoolManagementSystem.Role.Roles;
import com.example.SchoolManagementSystem.Users.Dto.NewUserDto;
import com.example.SchoolManagementSystem.Utils.Email.EmailService;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;

import java.util.*;

@Controller
public class UserService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleService roleService;

    @Autowired
    EmailService emailService;


    private final static String USER_NOT_FOUND = "user with the email %s not found";

    public User createUser(NewUserDto newUserDto) throws UnirestException {
        Optional<User> userOptional = userRepository.findUserByEmail(newUserDto.getEmail());
        if (userOptional.isPresent()) throw new IllegalStateException("User already exists");

        User user = new User(newUserDto.getFirstName(), newUserDto.getLastName(), newUserDto.getEmail(), newUserDto.getPhoneNumber(), newUserDto.getPassword());

        String confirmToken = UUID.randomUUID().toString();
        user.setConfirmToken(confirmToken);

        List<UUID> roleIds = newUserDto.getRoleId();
        Set<Roles> roles = new HashSet<>();

        roleIds.forEach(roleId -> {
            Roles userRole = roleService.findRoleByTd(roleId);
            roles.add(userRole);
        });

        user.setRoles(roles);
        User newUser = userRepository.save(user);

        emailService.sendEmailToUser(newUser, EnumEmailContent.RegistrantEmail);
        return newUser;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User findUserByConfirmToken(String token) {
        return userRepository.findUserByConfirmToken(token)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with token: " + token));
    }

    public User findUserByEmail(String email) {
        return userRepository.findUserByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found"));
    }

    public User findUserByAccessToken(String accessToken) {
        return userRepository.findUserByAccessToken(accessToken)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found"));

    }

    public User GetUser(UUID id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) return userOptional.get();
        throw new IllegalStateException("user not found");
    }

    public User UpdateUser(User user, UUID id) {
        User userOptional = userRepository.findById(id).
                orElseThrow(() -> new IllegalStateException("User not found on :: " + id));

        userOptional.setFirstName(user.getFirstName());
        userOptional.setLastName(user.getLastName());
        userOptional.setPhoneNumber(user.getPhoneNumber());
        userOptional.setEmail(user.getEmail());
        userOptional.setAccessToken(user.getAccessToken());

        return userRepository.save(userOptional);
    }

    public User updateStatus(UUID id, Boolean status) {
        User user = GetUser(id);

        user.setEnabled(status);
        return userRepository.save(user);
    }

    public String DeleteUser(UUID id) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalStateException("User not found on :: " + id));
        userRepository.delete(user);
        return "deleted successfully";
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findUserByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with provided email: " + email));
        return UserDetailsImpl.build(user);
    }

}
