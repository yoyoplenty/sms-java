package com.example.SchoolManagementSystem.Student;

import com.example.SchoolManagementSystem.Enum.EnumUserType;
import com.example.SchoolManagementSystem.Role.RoleService;
import com.example.SchoolManagementSystem.Role.Roles;
import com.example.SchoolManagementSystem.School.School;
import com.example.SchoolManagementSystem.School.SchoolService;
import com.example.SchoolManagementSystem.Student.Dto.NewStudentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    RoleService roleService;
    @Autowired
    SchoolService schoolService;

    public Student createStudent(NewStudentDto newStudentDto) {
        Optional<Student> student = studentRepository.
                findStudentByFirstNameAndLastNameAndMiddleName(newStudentDto.getFirstName(),
                        newStudentDto.getLastName(), newStudentDto.getMiddleName());
        if (student.isPresent()) throw new IllegalStateException("user with those names already present");

        School school = schoolService.findSchoolById(newStudentDto.getSchoolId());
        Roles role = roleService.findRoleByTd(newStudentDto.getRoleId());

        Student newStudent = Student.builder()
                .firstName(newStudentDto.getFirstName())
                .lastName(newStudentDto.getLastName())
                .middleName(newStudentDto.getMiddleName())
                .studentId(UUID.randomUUID().toString().substring(0, 6))
                .phoneNumber(newStudentDto.getPhoneNumber())
                .password(newStudentDto.getPassword())
                .userType(EnumUserType.STUDENT)
                .roles(role)
                .enabled(true)
                .locked(false)
                .school(school)
                .build();

        return studentRepository.save(newStudent);
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student getStudent(String id) {
        return studentRepository.findByStudentId(id)
                .orElseThrow(() -> new IllegalStateException("student not found"));
    }

    public Student findStudentByStudentId(String studentId) {
        return studentRepository.findStudentByStudentId(studentId);
    }

}
