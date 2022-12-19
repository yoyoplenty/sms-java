package com.example.SchoolManagementSystem.Student;


import com.example.SchoolManagementSystem.School.Annotation.SchoolNotPresent;
import com.example.SchoolManagementSystem.Student.Dto.NewStudentDto;
import com.example.SchoolManagementSystem.Student.Dto.UpdateStudentDto;
import com.example.SchoolManagementSystem.Utils.Handlers.Responses.ResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

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

            return ResponseHandler.generateResponse("Successfully fetched data", HttpStatus.OK, students);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY, e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getStudent(@PathVariable UUID id) {
        try {
            Student student = studentService.getStudent(id);

            return ResponseHandler.generateResponse("Successfully created teacher", HttpStatus.OK, student);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY, e.getMessage());
        }
    }

    @GetMapping("/schools/{school_id}")
    public ResponseEntity<Object> getSchoolStudents(@PathVariable @SchoolNotPresent UUID school_id) {
        try {
            List<Student> students = studentService.findStudentsBySchoolId(school_id);

            return ResponseHandler.generateResponse("Successfully fetched school students", HttpStatus.OK, students);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY, e.getMessage());
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Object> updateStudent(@RequestBody UpdateStudentDto updateStudentDto, @PathVariable UUID id) {
        try {
            Object student = studentService.updateStudent(updateStudentDto, id);

            return ResponseHandler.generateResponse("Successfully updated data", HttpStatus.OK, student);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY, e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteStudent(@PathVariable UUID id) {
        try {
            Object student = studentService.deleteStudent(id);

            return ResponseHandler.generateResponse("Successfully deleted data", HttpStatus.OK, student);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY, e.getMessage());
        }
    }
}
