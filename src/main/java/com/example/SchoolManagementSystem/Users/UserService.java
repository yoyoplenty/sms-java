package com.example.SchoolManagementSystem.Users;

import com.example.SchoolManagementSystem.Auth.Security.Service.UserDetailsImpl;
import com.example.SchoolManagementSystem.Role.RoleService;
import com.example.SchoolManagementSystem.Role.Roles;
import com.example.SchoolManagementSystem.Users.Dto.NewUserDto;
import com.example.SchoolManagementSystem.Utils.Email.Dto.MailData;
import com.example.SchoolManagementSystem.Utils.Email.EmailService;
import com.example.SchoolManagementSystem.Utils.Helpers.TokenService;
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
    @Autowired
    TokenService tokenService;

    private final static String USER_NOT_FOUND = "user with the email %s not found";

    public User createUser(NewUserDto newUserDto) throws UnirestException {
        Optional<User> userOptional = userRepository.findUserByEmail(newUserDto.getEmail());
        if (userOptional.isPresent()) throw new IllegalStateException("User already exists");

        User user = new User(newUserDto.getFirstName(), newUserDto.getLastName(), newUserDto.getEmail(), newUserDto.getPhoneNumber(), newUserDto.getPassword());

        String token = UUID.randomUUID().toString();
        user.setConfirmToken(tokenService.generateToken(token));

        List<UUID> roleIds = newUserDto.getRoleId();
        Set<Roles> roles = new HashSet<>();

        roleIds.forEach(roleId -> {
            Roles userRole = roleService.findRoleByTd(roleId);
            roles.add(userRole);
        });

        user.setRoles(roles);
        User newUser = userRepository.save(user);

        MailData emailData = EmailData(newUser);
        emailService.sendEmail(emailData);

        return newUser;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User GetUser(UUID id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) return userOptional.get();
        throw new IllegalStateException("user not found");
    }

    public User UpdateUser(User user, UUID id) {

        User userOptional = userRepository.findById(id).orElseThrow(() -> new IllegalStateException("User not found on :: " + id));

        userOptional.setFirstName(user.getFirstName());
        userOptional.setLastName(user.getLastName());
        userOptional.setPhoneNumber(user.getPhoneNumber());
        userOptional.setEmail(user.getEmail());

        return userRepository.save(userOptional);
    }

    public String DeleteUser(UUID id) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalStateException("User not found on :: " + id));
        userRepository.delete(user);
        return "deleted successfully";
    }

    public MailData EmailData(User user) {
        return MailData.builder()
                .receiver(user.getEmail())
                .body("You have Successfully registered")
                .subject("Welcome Here " + user.getFirstName())
                .build();

    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findUserByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with provided email: " + email));
        return UserDetailsImpl.build(user);
    }
}
