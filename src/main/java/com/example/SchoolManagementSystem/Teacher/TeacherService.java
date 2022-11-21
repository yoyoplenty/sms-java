package com.example.SchoolManagementSystem.Teacher;

import com.example.SchoolManagementSystem.School.School;
import com.example.SchoolManagementSystem.School.SchoolService;
import com.example.SchoolManagementSystem.Subject.Subject;
import com.example.SchoolManagementSystem.Subject.SubjectService;
import com.example.SchoolManagementSystem.Teacher.Dto.NewTeacherDto;
import com.example.SchoolManagementSystem.Teacher.Dto.UpdateTeacherDto;
import com.example.SchoolManagementSystem.Users.User;
import com.example.SchoolManagementSystem.Users.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TeacherService {

    @Autowired
    TeacherRepository teacherRepository;

    @Autowired
    UserService userService;

    @Autowired
    SchoolService schoolService;

    @Autowired
    SubjectService subjectService;

    private static final Logger logger = LoggerFactory.getLogger(TeacherService.class);


    public Teacher createTeacher(NewTeacherDto newTeacherDto) {
        School school = schoolService.findSchoolById(newTeacherDto.getSchoolId());
        User user = userService.GetUser(newTeacherDto.getUserId());

        Optional<Teacher> teacher = teacherRepository.findTeacherByUserId(user.getId());
        if (teacher.isPresent()) throw new IllegalStateException("teacher with that userId already exists");

        Teacher newTeacher = Teacher.builder()
                .middleName(newTeacherDto.getMiddleName())
                .staffId(UUID.randomUUID().toString().substring(0, 5))
                .user(user)
                .school(school)
                .build();

        List<UUID> subjectIds = newTeacherDto.getSubjectId();
        List<Subject> subjects = new ArrayList<>();

        subjectIds.forEach(subjectId -> {
            Subject subject = subjectService.findSubjectById(subjectId);

            List<Teacher> subjectPresent = teacherRepository.findSubjectInTeacher(subjectId);
            if (subjectPresent.size() > 0) throw new IllegalStateException("Subject already assigned teacher");

            subjects.add(subject);
        });

        newTeacher.setSubjects(subjects);
        return teacherRepository.save(newTeacher);
    }

    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }

    public Teacher getTeacher(UUID id) {
        Optional<Teacher> teacherOptional = teacherRepository.findById(id);
        if (teacherOptional.isPresent()) return teacherOptional.get();
        throw new IllegalStateException("teacher not found");
    }

    public Teacher updateTeacher(UpdateTeacherDto updateTeacherDto, UUID id) {
        Teacher teacherOptional = teacherRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("teacher not found on :: " + id));

        List<Subject> teacherSubjects = teacherOptional.getSubjects();

        if (updateTeacherDto.getSubjectId() != null || updateTeacherDto.getSubjectId().size() > 0) {
            List<UUID> subjectIds = updateTeacherDto.getSubjectId();

            for (UUID subjectId : subjectIds) {
                Subject subject = subjectService.findSubjectById(subjectId);

                List<Teacher> subjectPresent = teacherRepository.findSubjectInTeacher(subjectId);
                if (subjectPresent.size() > 0) throw new IllegalStateException("Subject already assigned to teacher");

                teacherSubjects.add(subject);
            }
        }
        teacherOptional.setMiddleName(teacherOptional.getMiddleName());
        teacherOptional.setSubjects(teacherSubjects);

        return teacherRepository.save(teacherOptional);
    }
}
