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

        User userOptional = userRepository.findById(id).orElseThrow(() -> new IllegalStateException("User not found on :: " + id));

        userOptional.setFirstName(user.getFirstName());
        userOptional.setLastName(user.getLastName());
        userOptional.setPhoneNumber(user.getPhoneNumber());
        userOptional.setEmail(user.getEmail());

        return userRepository.save(userOptional);
    }

    public String DeleteUser(int id) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalStateException("User not found on :: " + id));
        userRepository.delete(user);
        return "deleted successfully";
    }
}
