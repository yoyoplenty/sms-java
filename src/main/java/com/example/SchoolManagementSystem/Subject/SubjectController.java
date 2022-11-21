package com.example.SchoolManagementSystem.Subject;

import com.example.SchoolManagementSystem.Subject.Dto.NewSubjectDto;
import com.example.SchoolManagementSystem.Utils.Handlers.Responses.ResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/subjects")
public class SubjectController {
    @Autowired
    SubjectService subjectService;

    @PostMapping()
    public ResponseEntity<Object> createSubject(@RequestBody @Valid NewSubjectDto subject) {
        try {
            Subject newSubject = subjectService.createSubject(subject);

            return ResponseHandler.generateResponse("Successfully created teacher", HttpStatus.OK, newSubject);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY, e.getMessage());
        }
    }

    @GetMapping()
    public ResponseEntity<Object> getSubjects() {
        try {
            List<Subject> allSubjects = subjectService.getAllSubject();
            return ResponseHandler.generateResponse("Successfully retrieved data!", HttpStatus.OK, allSubjects);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY, e.getMessage());
        }
    }


}
