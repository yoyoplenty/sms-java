package com.example.SchoolManagementSystem.Users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
public class UserService {

    @Autowired
    UserRepository userRepository;

//    public UserService(UserRepository userRepository) { this.userRepository = userRepository; }

    public User createUser(User user) {
        Optional<User> userOptional = userRepository.findUserByEmail(user.getEmail());
        if (userOptional.isPresent()) throw new IllegalStateException("User already exists");

        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User GetUser(int id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) return userOptional.get();
        throw new IllegalStateException("user not found");
    }

    public User UpdateUser(User user, int id) {

        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isEmpty()) throw new RuntimeException("user not found");

        userOptional.get().setFirstName(user.getFirstName());
        userOptional.get().setLastName(user.getLastName());
        userOptional.get().setPhoneNumber(user.getPhoneNumber());
        userOptional.get().setEmail(user.getEmail());

        return userRepository.save(user);
    }
//
//    public String DeleteUser(int id) {
//    }
}
