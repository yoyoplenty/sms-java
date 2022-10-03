package com.example.SchoolManagementSystem.Users;

import com.example.SchoolManagementSystem.Responses.ResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/user")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping()
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
        try {
            User newUser = userService.createUser(user);
            return ResponseHandler.generateResponse("Successfully created user!", HttpStatus.OK, newUser);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }

    @GetMapping()
    public ResponseEntity<Object> getUsers() {
        try {
            List<User> allUsers = userService.getAllUsers();
//            System.out.println(allUsers);
            return ResponseHandler.generateResponse("Successfully retrieved data!", HttpStatus.OK, allUsers);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> GetOneUser(@PathVariable int id) {
        try {
            User userGotten = userService.GetUser(id);
            return ResponseHandler.generateResponse("Successfully retrieved data!", HttpStatus.OK, userGotten);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Object> UpdateUser(@PathVariable int id, @RequestBody User user) {
        try {
            User updatedUser = userService.UpdateUser(user, id);
            return ResponseHandler.generateResponse("Updated", HttpStatus.OK, updatedUser);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> Delete(@PathVariable int id) {
        try {
            String result = userService.DeleteUser(id);
            return ResponseHandler.generateResponse("Deleted!", HttpStatus.OK, result);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }
}
