package com.example.SchoolManagementSystem.Student;


import com.example.SchoolManagementSystem.Student.Dto.NewStudentDto;
import com.example.SchoolManagementSystem.Utils.Handlers.Responses.ResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/students")
public class StudentController {

    @Autowired
    PasswordEncoder encoder;
    @Autowired
    StudentService studentService;

    @PostMapping()
    public ResponseEntity<Object> createStudent(@RequestBody @Valid NewStudentDto newStudentDto) {
        try {
            newStudentDto.setPassword(encoder.encode(newStudentDto.getPassword()));
            Student newStudent = studentService.createStudent(newStudentDto);

            return ResponseHandler.generateResponse("Successfully created student", HttpStatus.OK, newStudent);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY, e.getMessage());
        }
    }


    @GetMapping()
    public ResponseEntity<Object> getStudents() {
        try {
            List<Student> students = studentService.getAllStudents();

            return ResponseHandler.generateResponse("Successfully created teacher", HttpStatus.OK, students);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY, e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getStudent(@PathVariable String id) {
        try {
            Student student = studentService.getStudent(id);

            return ResponseHandler.generateResponse("Successfully created teacher", HttpStatus.OK, student);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY, e.getMessage());
        }
    }
}
