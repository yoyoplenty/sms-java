package com.example.SchoolManagementSystem.Teacher;

import com.example.SchoolManagementSystem.Address.Address;
import com.example.SchoolManagementSystem.Address.AddressService;
import com.example.SchoolManagementSystem.Enum.EnumUserType;
import com.example.SchoolManagementSystem.School.School;
import com.example.SchoolManagementSystem.School.SchoolService;
import com.example.SchoolManagementSystem.Subject.Subject;
import com.example.SchoolManagementSystem.Subject.SubjectService;
import com.example.SchoolManagementSystem.Teacher.Dto.NewTeacherDto;
import com.example.SchoolManagementSystem.Teacher.Dto.UpdateTeacherDto;
import com.example.SchoolManagementSystem.Users.User;
import com.example.SchoolManagementSystem.Users.UserService;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class TeacherService {
    @Autowired
    TeacherRepository teacherRepository;

    @Autowired
    UserService userService;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    SchoolService schoolService;

    @Autowired
    SubjectService subjectService;

    @Autowired
    AddressService addressService;

    private static final Logger logger = LoggerFactory.getLogger(TeacherService.class);

    public Teacher createTeacher(NewTeacherDto newTeacherDto) throws UnirestException {
        School school = schoolService.findSchoolById(newTeacherDto.getSchoolId());

        newTeacherDto.setUserType(EnumUserType.TEACHER);
        Address address = addressService.createAddress(newTeacherDto.getAddress());

        Teacher newTeacher = Teacher.builder()
                .firstName(newTeacherDto.getFirstName())
                .lastName(newTeacherDto.getLastName())
                .middleName(newTeacherDto.getMiddleName())
                .phoneNumber(newTeacherDto.getPhoneNumber())
                .staffId(UUID.randomUUID().toString().substring(0, 6))
                .school(school)
                .address(address)
                .build();

        newTeacherDto.setPassword(encoder.encode(newTeacherDto.getPassword()));
        User user = userService.createUser(newTeacherDto);

        newTeacher.setSubjects(addSubjectToTeacher(newTeacherDto, newTeacher));
        newTeacher.setUser(user);

        return teacherRepository.save(newTeacher);
    }

    public List<Teacher> getTeachers() {
        return teacherRepository.findAll();
    }

    public List<Teacher> getTeachersBySchoolId(UUID schoolId) {
        schoolService.findSchoolById(schoolId);

        return teacherRepository.findTeacherBySchoolId(schoolId);
    }

    public Teacher findTeacherById(UUID id) {
        return teacherRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Teacher not found on :: " + id));
    }

    public Teacher updateTeacher(UpdateTeacherDto updateTeacherDto, UUID id) {
        Teacher teacherOptional = findTeacherById(id);

        if (updateTeacherDto.getSubjectId() != null || updateTeacherDto.getSubjectId().size() > 0)
            teacherOptional.setSubjects(addSubjectToTeacher(updateTeacherDto, teacherOptional));

        Address address = updateTeacherDto.getAddress() != null ?
                addressService.updateAddress(updateTeacherDto.getAddress(), teacherOptional.getAddress().getId())
                : teacherOptional.getAddress();

        teacherOptional.setFirstName(updateTeacherDto.getFirstName());
        teacherOptional.setLastName(updateTeacherDto.getLastName());
        teacherOptional.setMiddleName(updateTeacherDto.getMiddleName());
        teacherOptional.setPhoneNumber(updateTeacherDto.getPhoneNumber());
        teacherOptional.setAddress(address);

        return teacherRepository.save(teacherOptional);
    }

    public String deleteTeacher(UUID id) {
        Teacher teacherOptional = findTeacherById(id);
        teacherRepository.delete(teacherOptional);

        logger.info("User deleted successfully");
        return "deleted successfully";
    }

    public List<Subject> addSubjectToTeacher(NewTeacherDto newTeacherDto, Teacher newTeacher) {
        List<UUID> subjectIds = newTeacherDto.getSubjectId();
        List<Subject> subjects = newTeacher.getSubjects() != null ? newTeacher.getSubjects() : new ArrayList<>();

        subjectIds.forEach(subjectId -> {
            Subject subject = subjectService.findSubjectById(subjectId);

            Teacher subjectPresent = teacherRepository.findSubjectInTeacher(subjectId, newTeacher.getId());
            if (subjectPresent != null) throw new IllegalStateException("Subject already assigned teacher");

            subjects.add(subject);
        });

        return subjects;
    }
}
