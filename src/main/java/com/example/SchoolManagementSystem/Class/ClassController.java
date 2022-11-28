package com.example.SchoolManagementSystem.Class;


import com.example.SchoolManagementSystem.Class.Dto.NewClassDto;
import com.example.SchoolManagementSystem.Utils.Handlers.Responses.ResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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

}
