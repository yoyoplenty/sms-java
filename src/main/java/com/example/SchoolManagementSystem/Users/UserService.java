package com.example.SchoolManagementSystem.Users;

import com.example.SchoolManagementSystem.Auth.Security.Service.UserDetailsImpl;
import com.example.SchoolManagementSystem.Enum.EnumEmailContent;
import com.example.SchoolManagementSystem.Enum.EnumUserType;
import com.example.SchoolManagementSystem.Role.RoleService;
import com.example.SchoolManagementSystem.Role.Roles;
import com.example.SchoolManagementSystem.Users.Dto.NewUserDto;
import com.example.SchoolManagementSystem.Utils.Email.EmailService;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleService roleService;
    @Autowired
    EmailService emailService;

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);


    public User createUser(NewUserDto newUserDto) throws UnirestException {
        Boolean enabled = newUserDto.getEmail() == null;
        if (newUserDto.getUserType() == null) newUserDto.setUserType(EnumUserType.SYSTEM_ADMIN);

        User user = User.builder()
                .email(newUserDto.getEmail())
                .studentId(newUserDto.getStudentId())
                .password(newUserDto.getPassword())
                .userType(newUserDto.getUserType())
                .locked(false).enabled(enabled)
                .build();

        user.setConfirmToken(UUID.randomUUID().toString());

        List<UUID> roleIds = newUserDto.getRoleId();
        Set<Roles> roles = new HashSet<>();

        roleIds.forEach(roleId -> {
            Roles userRole = roleService.findRoleByTd(roleId);
            roles.add(userRole);
        });

        user.setRoles(roles);
        User newUser = userRepository.save(user);
        if (!enabled) emailService.sendEmailToUser(newUser, EnumEmailContent.RegistrationMail);

        return newUser;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User GetUser(UUID id) {
        return findUserById(id);
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User getUserById(UUID id) {
        return userRepository.findUserById(id);
    }

    public User findUserById(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found"));
    }

    public User findUserByEmail(String email) {
        return userRepository.findUserByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found"));
    }

    public User findUserByConfirmToken(String token) {
        return userRepository.findUserByConfirmToken(token)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with token: " + token));
    }

    public User findUserByResetToken(String resetToken) {
        return userRepository.findUserByResetToken(resetToken)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found"));

    }

    public User UpdateUser(User user, UUID id) {
        User userDetails = findUserById(id);

        //TODO must be encrypted before it gets here
//        user.setPassword();
        userDetails.setResetToken(user.getResetToken());
        userDetails.setEnabled(user.getEnabled());
        userDetails.setLocked(user.getLocked());
        userDetails.setRoles(user.getRoles());

        return userRepository.save(userDetails);
    }

    public User updateStatus(UUID id, Boolean status) {
        User user = GetUser(id);

        user.setEnabled(status);
        return userRepository.save(user);
    }

    public String DeleteUser(UUID id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("User not found on :: " + id));

        userRepository.delete(user);
        return "deleted successfully";
    }

    @Override
    public UserDetails loadUserByUsername(String data) throws UsernameNotFoundException {
        try {
            User user = userRepository.findByEmail(data);
            User studentUser = userRepository.findByStudentId(data);

            return user == null ? UserDetailsImpl.build(studentUser) : UserDetailsImpl.build(user);
        } catch (Exception e) {
            throw new IllegalStateException("invalid login credentials");
        }
    }

}
