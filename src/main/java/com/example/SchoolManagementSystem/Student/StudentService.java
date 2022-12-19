package com.example.SchoolManagementSystem.Student;

import com.example.SchoolManagementSystem.Address.Address;
import com.example.SchoolManagementSystem.Address.AddressService;
import com.example.SchoolManagementSystem.Class.Class;
import com.example.SchoolManagementSystem.Class.ClassService;
import com.example.SchoolManagementSystem.Enum.EnumUserType;
import com.example.SchoolManagementSystem.School.School;
import com.example.SchoolManagementSystem.School.SchoolService;
import com.example.SchoolManagementSystem.Student.Dto.NewStudentDto;
import com.example.SchoolManagementSystem.Student.Dto.UpdateStudentDto;
import com.example.SchoolManagementSystem.Teacher.TeacherService;
import com.example.SchoolManagementSystem.Users.User;
import com.example.SchoolManagementSystem.Users.UserService;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    ClassService classService;

    @Autowired
    UserService userService;

    @Autowired
    AddressService addressService;

    private static final Logger logger = LoggerFactory.getLogger(TeacherService.class);


    public Student createStudent(NewStudentDto newStudentDto) throws UnirestException {
        School school = schoolService.findSchoolById(newStudentDto.getSchoolId());
        Class clazz = classService.findClassById(newStudentDto.getClassId());
        Address address = addressService.createAddress(newStudentDto.getAddress());

        Student newStudent = Student.builder()
                .firstName(newStudentDto.getFirstName())
                .lastName(newStudentDto.getLastName())
                .middleName(newStudentDto.getMiddleName())
                .studentId(UUID.randomUUID().toString().substring(0, 6))
                .phoneNumber(newStudentDto.getPhoneNumber())
                .school(school)
                .address(address)
                .clazz(clazz)
                .build();

        newStudentDto.setUserType(EnumUserType.STUDENT);
        newStudentDto.setStudentId(newStudent.getStudentId());

        User user = userService.createUser(newStudentDto);
        newStudent.setUser(user);

        return studentRepository.save(newStudent);
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student getStudent(UUID id) {
        return findStudentById(id);
    }

    public List<Student> findStudentsBySchoolId(UUID id) {
        schoolService.findSchoolById(id);

        return studentRepository.findStudentBySchoolId(id);
    }

    public Student findStudentById(UUID id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("student not found"));
    }

    public Student findStudentByNames(String firstName, String lastName, String middleName) {
        return studentRepository.findByFirstNameAndLastNameAndMiddleName(firstName, lastName, middleName);
    }

    public Student updateStudent(UpdateStudentDto updateStudentDto, UUID id) {
        Class clazz = classService.findClassById(updateStudentDto.getClassId());
        Student student = findStudentById(id);

        Address address = updateStudentDto.getAddress() != null ?
                addressService.updateAddress(updateStudentDto.getAddress(), student.getAddress().getId()) :
                student.getAddress();

        student.setClazz(clazz);
        student.setFirstName(updateStudentDto.getFirstName());
        student.setLastName(updateStudentDto.getLastName());
        student.setMiddleName(updateStudentDto.getMiddleName());
        student.setPhoneNumber(updateStudentDto.getPhoneNumber());
        student.setAddress(address);

        return studentRepository.save(student);
    }

    public String deleteStudent(UUID id) {
        Student studentOptional = findStudentById(id);
        studentRepository.delete(studentOptional);

        logger.info("User deleted successfully");
        return "deleted successfully";
    }
}

//        Optional<Student> student = studentRepository.
//                findStudentByFirstNameAndLastNameAndMiddleName(newStudentDto.getFirstName(),
//                        newStudentDto.getLastName(), newStudentDto.getMiddleName());
//        if (student.isPresent()) throw new IllegalStateException("user with those names already present");
