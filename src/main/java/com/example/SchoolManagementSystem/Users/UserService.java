package com.example.SchoolManagementSystem.Users;

import com.example.SchoolManagementSystem.Auth.Security.Service.UserDetailsImpl;
import com.example.SchoolManagementSystem.Roles.RoleService;
import com.example.SchoolManagementSystem.Roles.Roles;
import com.example.SchoolManagementSystem.Users.Dto.NewUserDto;
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

    private final static String USER_NOT_FOUND = "user with the email %s not found";

    public User createUser(NewUserDto newUserDto) {
        Optional<User> userOptional = userRepository.findUserByEmail(newUserDto.getEmail());
        if (userOptional.isPresent()) throw new IllegalStateException("User already exists");

        User user = new User(newUserDto.getFirstName(), newUserDto.getLastName(), newUserDto.getEmail(), newUserDto.getPhoneNumber(), newUserDto.getPassword());

        List<UUID> roleIds = newUserDto.getRoleId();
        Set<Roles> roles = new HashSet<>();

        roleIds.forEach(roleId -> {
            Roles userRole = roleService.findRoleByTd(roleId);
            roles.add(userRole);
        });

        user.setRoles(roles);
        return userRepository.save(user);
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

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findUserByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with provided email: " + email));
        return UserDetailsImpl.build(user);
    }
}
