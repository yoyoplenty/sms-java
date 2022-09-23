package com.example.SchoolManagementSystem.Schools;

import com.example.SchoolManagementSystem.Responses.ResponseHandler;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/v1/school")
public class SchoolController {
    @Autowired
    SchoolService schoolService;

    @PostMapping()
    public ResponseEntity<Object> createSchool(@Valid @RequestBody School school) {
        try {
            School newSchool = schoolService.createSchool(school);
            return ResponseHandler.generateResponse("Successfully created school!", HttpStatus.OK, newSchool);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }

    @GetMapping()
    public ResponseEntity<Object> getSchools() {
        try {
            List<School> allSchools = schoolService.getAllSchools();
//            System.out.println(allUsers);
            return ResponseHandler.generateResponse("Successfully retrieved data!", HttpStatus.OK, allSchools);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneSchool(@PathVariable int id) {
        try {
            School schoolGotten = schoolService.getSchool(id);
            return ResponseHandler.generateResponse("Successfully retrieved data!", HttpStatus.OK, schoolGotten);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Object> updateSchool(@PathVariable int id, @RequestBody School school) {
        try {
            School updatedSchool = schoolService.updateSchool(school, id);
            return ResponseHandler.generateResponse("Updated", HttpStatus.OK, updatedSchool);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteSchool(@PathVariable int id) {
        try {
            String result = schoolService.deleteSchool(id);
            return ResponseHandler.generateResponse("Deleted!", HttpStatus.OK, result);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }
}
