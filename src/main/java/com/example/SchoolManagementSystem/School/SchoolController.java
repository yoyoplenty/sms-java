package com.example.SchoolManagementSystem.School;

import com.example.SchoolManagementSystem.School.Annotation.SchoolNotPresent;
import com.example.SchoolManagementSystem.School.Dto.NewSchoolDto;
import com.example.SchoolManagementSystem.School.Dto.UpdateSchoolDto;
import com.example.SchoolManagementSystem.Utils.Handlers.Responses.ResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("api/v1/schools")
public class SchoolController {
    @Autowired
    SchoolService schoolService;

    @PostMapping()
    public ResponseEntity<Object> createSchool(@Valid @RequestBody NewSchoolDto newSchoolDto) {
        try {
            School newSchool = schoolService.createSchool(newSchoolDto);
            return ResponseHandler.generateResponse("Successfully created school!", HttpStatus.OK, newSchool);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, e.getMessage());
        }
    }

    @GetMapping()
    public ResponseEntity<Object> getSchools() {
        try {
            List<School> allSchools = schoolService.getAllSchools();

            return ResponseHandler.generateResponse("Successfully retrieved data!", HttpStatus.OK, allSchools);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneSchool(@PathVariable @SchoolNotPresent UUID id) {
        try {
            School schoolGotten = schoolService.getSchool(id);

            return ResponseHandler.generateResponse("Successfully retrieved data!", HttpStatus.OK, schoolGotten);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, e.getMessage());
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Object> updateSchool(@PathVariable @SchoolNotPresent UUID id, @RequestBody UpdateSchoolDto updateSchoolDto) {
        try {
            School updatedSchool = schoolService.updateSchool(updateSchoolDto, id);

            return ResponseHandler.generateResponse("Updated", HttpStatus.OK, updatedSchool);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, e.getMessage());
        }
    }

    @DeleteMapping("/address/{address_id}")
    public ResponseEntity<Object> deleteAddressInSchool(@PathVariable @SchoolNotPresent UUID address_id) {
        try {
            School school = schoolService.deleteAddressInSchool(address_id);
            return ResponseHandler.generateResponse("Deleted address successfully!", HttpStatus.OK, school);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteSchool(@PathVariable @SchoolNotPresent UUID id) {
        try {
            String result = schoolService.deleteSchool(id);
            return ResponseHandler.generateResponse("Deleted!", HttpStatus.OK, result);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, e.getMessage());
        }
    }
}
