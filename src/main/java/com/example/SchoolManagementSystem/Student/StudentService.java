package com.example.SchoolManagementSystem.Student;

import com.example.SchoolManagementSystem.Enum.EnumUserType;
import com.example.SchoolManagementSystem.Role.RoleService;
import com.example.SchoolManagementSystem.Role.Roles;
import com.example.SchoolManagementSystem.Student.Dto.NewStudentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    RoleService roleService;

    public Student createStudent(NewStudentDto newStudentDto) {
        //TODO check for validation of school to be added

        Roles role = roleService.findRoleByTd(newStudentDto.getRoleId());

        Student student = Student.builder()
                .firstName(newStudentDto.getFirstName())
                .lastName(newStudentDto.getLastName())
                .middleName(newStudentDto.getMiddleName())
                .studentId(UUID.randomUUID().toString().substring(0, 5))
                .phoneNumber(newStudentDto.getPhoneNumber())
                .password(newStudentDto.getPassword())
                .userType(EnumUserType.Student)
                .roles(role)
                .enabled(true)
                .locked(false)
//                .school()
                .build();

        return studentRepository.save(student);
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student findStudentByStudentId(String studentId) {
        return studentRepository.findStudentByStudentId(studentId);
    }

    public Student getStudent(String id) {
        return studentRepository.findStudentByStudentId(id);
    }
}
