package com.example.SchoolManagementSystem.Role;

import com.example.SchoolManagementSystem.Utils.Handlers.Responses.ResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/roles")
public class RoleController {
    @Autowired
    RoleService roleService;

    @PostMapping()
    public ResponseEntity<Object> create(@Valid @RequestBody Roles role) {
        try {
            Roles newRole = roleService.createRole(role);

            return ResponseHandler.generateResponse("Successfully created role!", HttpStatus.OK, newRole);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY, e.getMessage());
        }
    }

    @GetMapping()
    public ResponseEntity<Object> findALL() {
        try {
            List<Roles> allRoles = roleService.getAllRoles();

            return ResponseHandler.generateResponse("Successfully retrieved data!", HttpStatus.OK, allRoles);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY, e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findOne(@PathVariable UUID id) {
        try {
            Roles role = roleService.getRole(id);

            return ResponseHandler.generateResponse("Successfully retrieved data!", HttpStatus.OK, role);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY, e.getMessage());
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable UUID id, @RequestBody Roles role) {
        try {
            Roles updatedRole = roleService.UpdateRole(role, id);

            return ResponseHandler.generateResponse("updated successfully", HttpStatus.OK, updatedRole);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY, e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> remove(@PathVariable UUID id) {
        try {
            String result = roleService.DeleteRole(id);

            return ResponseHandler.generateResponse("User deleted successfully!", HttpStatus.OK, result);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY, e.getMessage());
        }
    }

}
