package com.example.SchoolManagementSystem.Users;

import com.example.SchoolManagementSystem.Utils.Handlers.Responses.ResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/users")
public class UserController {
    @Autowired
    UserService userService;
    
    @GetMapping()
    public ResponseEntity<Object> getUsers() {
        try {
            List<User> allUsers = userService.getAllUsers();
            return ResponseHandler.generateResponse("Successfully retrieved data!", HttpStatus.OK, allUsers);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY, e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> GetOneUser(@PathVariable UUID id) {
        try {
            User userGotten = userService.GetUser(id);
            return ResponseHandler.generateResponse("Successfully retrieved data!", HttpStatus.OK, userGotten);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY, e.getMessage());
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Object> UpdateUser(@PathVariable UUID id, @RequestBody User user) {
        try {
            User updatedUser = userService.UpdateUser(user, id);
            return ResponseHandler.generateResponse("User updated successfully", HttpStatus.OK, updatedUser);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY, e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> Delete(@PathVariable UUID id) {
        try {
            String result = userService.DeleteUser(id);
            return ResponseHandler.generateResponse("User deleted successfully!", HttpStatus.OK, result);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY, e.getMessage());
        }
    }
}

//TODO user should not be created here

//    @PostMapping()
//    public ResponseEntity<Object> createUser(@Valid @RequestBody NewUserDto user) {
//        try {
//            User newUser = userService.createUser(user);
//            return ResponseHandler.generateResponse("Successfully created user!", HttpStatus.OK, newUser);
//        } catch (Exception e) {
//            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
//        }
//    }