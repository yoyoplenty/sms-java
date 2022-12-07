package com.example.SchoolManagementSystem.Subject;

import com.example.SchoolManagementSystem.School.Annotation.SchoolNotPresent;
import com.example.SchoolManagementSystem.Subject.Dto.NewSubjectDto;
import com.example.SchoolManagementSystem.Subject.Dto.UpdateSubjectDto;
import com.example.SchoolManagementSystem.Utils.Handlers.Responses.ResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

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

    @GetMapping("/{id}")
    public ResponseEntity<Object> getSubject(@PathVariable UUID id) {
        try {
            Object subject = subjectService.getSubject(id);

            return ResponseHandler.generateResponse("Successfully fetched data", HttpStatus.OK, subject);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY, e.getMessage());
        }
    }

    @GetMapping("/schools/{school_id}")
    public ResponseEntity<Object> getSchoolSubjects(@PathVariable @SchoolNotPresent UUID school_id) {
        try {
            List<Subject> subjects = subjectService.getSubjectsBySchoolId(school_id);

            return ResponseHandler.generateResponse("Successfully fetched school subjects",
                    HttpStatus.OK, subjects);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY, e.getMessage());
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Object> updateSubject(@RequestBody UpdateSubjectDto updateSubjectDto, @PathVariable UUID id) {
        try {
            Object subject = subjectService.updateSubject(updateSubjectDto, id);

            return ResponseHandler.generateResponse("Successfully updated data", HttpStatus.OK, subject);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY, e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteSubject(@PathVariable UUID id) {
        try {
            Object subject = subjectService.deleteSubject(id);

            return ResponseHandler.generateResponse("Successfully deleted data", HttpStatus.OK, subject);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY, e.getMessage());
        }
    }
}
