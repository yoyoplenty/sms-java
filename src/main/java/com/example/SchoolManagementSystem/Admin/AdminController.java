package com.example.SchoolManagementSystem.Admin;

import com.example.SchoolManagementSystem.Admin.Dto.NewAdminDto;
import com.example.SchoolManagementSystem.Utils.Handlers.Responses.ResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/admins")
public class AdminController {
    @Autowired
    AdminService adminService;

    @PostMapping()
    public ResponseEntity<Object> createAdmin(@RequestBody @Valid NewAdminDto newAdminDto) {
        try {
            Admin newAdmin = adminService.createAdmin(newAdminDto);

            return ResponseHandler.generateResponse("Successfully created admin", HttpStatus.OK, newAdmin);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY, e.getMessage());
        }
    }

    @GetMapping()
    public ResponseEntity<Object> getAdmins() {
        try {
            List<Admin> admins = adminService.getAllAdmins();

            return ResponseHandler.generateResponse("Successfully fetched admins", HttpStatus.OK, admins);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY, e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneTeacher(@PathVariable UUID id) {
        try {
            Object admin = adminService.findAdminById(id);

            return ResponseHandler.generateResponse("Successfully updated teacher", HttpStatus.OK, admin);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY, e.getMessage());
        }
    }
}