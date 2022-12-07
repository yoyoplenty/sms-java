package com.example.SchoolManagementSystem.Class;


import com.example.SchoolManagementSystem.Class.Dto.NewClassDto;
import com.example.SchoolManagementSystem.Class.Dto.UpdateClassDto;
import com.example.SchoolManagementSystem.School.Annotation.SchoolNotPresent;
import com.example.SchoolManagementSystem.Utils.Handlers.Responses.ResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/class")
public class ClassController {

    @Autowired
    ClassService classService;

    @PostMapping()
    public ResponseEntity<Object> createClass(@RequestBody @Valid NewClassDto newClassDto) {
        try {
            Class newClass = classService.createClass(newClassDto);

            return ResponseHandler.generateResponse("Successfully created", HttpStatus.OK, newClass);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY, e.getMessage());
        }
    }

    @GetMapping()
    public ResponseEntity<Object> getClasses() {
        try {
            List<Class> allClass = classService.getClasses();

            return ResponseHandler.generateResponse("Successfully fetched data", HttpStatus.OK, allClass);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY, e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getClass(@PathVariable UUID id) {
        try {
            Object classGotten = classService.getClass(id);

            return ResponseHandler.generateResponse("Successfully fetched data", HttpStatus.OK, classGotten);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY, e.getMessage());
        }
    }

    @GetMapping("/schools/{school_id}")
    public ResponseEntity<Object> getSchoolClasses(@PathVariable @SchoolNotPresent UUID school_id) {
        try {
            List<Class> classes = classService.getClassBySchoolId(school_id);

            return ResponseHandler.generateResponse("Successfully fetched school classes",
                    HttpStatus.OK, classes);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY, e.getMessage());
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Object> updateClass(@RequestBody UpdateClassDto updateClassDto, @PathVariable UUID id) {
        try {
            Object clazz = classService.updateClass(updateClassDto, id);

            return ResponseHandler.generateResponse("Successfully updated teacher", HttpStatus.OK, clazz);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY, e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteClass(@PathVariable UUID id) {
        try {
            Object deletedClass = classService.deleteClass(id);

            return ResponseHandler.generateResponse("Successfully deleted data", HttpStatus.OK, deletedClass);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY, e.getMessage());
        }
    }
}
