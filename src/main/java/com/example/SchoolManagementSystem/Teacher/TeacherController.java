package com.example.SchoolManagementSystem.Teacher;


import com.example.SchoolManagementSystem.Teacher.Dto.NewTeacherDto;
import com.example.SchoolManagementSystem.Teacher.Dto.UpdateTeacherDto;
import com.example.SchoolManagementSystem.Utils.Handlers.Responses.ResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/teachers")
public class TeacherController {

    @Autowired
    TeacherService teacherService;

    @PostMapping()
    public ResponseEntity<Object> createTeacher(@RequestBody @Valid NewTeacherDto newTeacherDto) {
        try {
            Teacher newTeacher = teacherService.createTeacher(newTeacherDto);

            return ResponseHandler.generateResponse("Successfully created teacher", HttpStatus.OK, newTeacher);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY, e.getMessage());
        }
    }

    @GetMapping()
    public ResponseEntity<Object> createTeacher() {
        try {
            List<Teacher> teacher = teacherService.getAllTeachers();

            return ResponseHandler.generateResponse("Successfully created teacher", HttpStatus.OK, teacher);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY, e.getMessage());
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Object> updateTeacher(@RequestBody UpdateTeacherDto updateTeacherDto, @PathVariable UUID id) {
        try {
            Object teacher = teacherService.updateTeacher(updateTeacherDto, id);

            return ResponseHandler.generateResponse("Successfully updated teacher", HttpStatus.OK, teacher);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY, e.getMessage());
        }
    }
}
