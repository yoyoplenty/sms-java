package com.example.SchoolManagementSystem.Student;

import com.example.SchoolManagementSystem.Enum.EnumUserType;
import com.example.SchoolManagementSystem.School.School;
import com.example.SchoolManagementSystem.School.SchoolService;
import com.example.SchoolManagementSystem.Student.Dto.NewStudentDto;
import com.example.SchoolManagementSystem.Users.User;
import com.example.SchoolManagementSystem.Users.UserService;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    SchoolService schoolService;

    @Autowired
    UserService userService;

    public Student createStudent(NewStudentDto newStudentDto) throws UnirestException {
        //TODO validate existing student with annotation
//        Optional<Student> student = studentRepository.
//                findStudentByFirstNameAndLastNameAndMiddleName(newStudentDto.getFirstName(),
//                        newStudentDto.getLastName(), newStudentDto.getMiddleName());
//        if (student.isPresent()) throw new IllegalStateException("user with those names already present");

        School school = schoolService.findSchoolById(newStudentDto.getSchoolId());
        newStudentDto.setUserType(EnumUserType.STUDENT);

        Student newStudent = Student.builder()
                .firstName(newStudentDto.getFirstName())
                .lastName(newStudentDto.getLastName())
                .middleName(newStudentDto.getMiddleName())
                .studentId(UUID.randomUUID().toString().substring(0, 6))
                .phoneNumber(newStudentDto.getPhoneNumber())
                .school(school)
                .build();

        newStudentDto.setStudentId(newStudent.getStudentId());
        User user = userService.createUser(newStudentDto);
        newStudent.setUser(user);

        return studentRepository.save(newStudent);
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student getStudent(String id) {
        return studentRepository.findByStudentId(id)
                .orElseThrow(() -> new IllegalStateException("student not found"));
    }
    
    public Student findStudentByNames(String firstName, String lastName, String middleName) {
        return studentRepository.findByFirstNameAndLastNameAndMiddleName(firstName, lastName, middleName);
    }

}
