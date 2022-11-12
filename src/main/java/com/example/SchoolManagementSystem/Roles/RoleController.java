package com.example.SchoolManagementSystem.Roles;

import com.example.SchoolManagementSystem.Responses.ResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("api/v1/roles")
public class RoleController {
    @Autowired
    RoleService roleService;

    @PostMapping()
    public ResponseEntity<Object> createRole(@Valid @RequestBody Roles role) {
        try {
            roleService.roleRepository.findRoleById(role.getId());
            Roles newRole = roleService.createRole(role);

            return ResponseHandler.generateResponse("Successfully created role!", HttpStatus.OK, newRole);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }


}
