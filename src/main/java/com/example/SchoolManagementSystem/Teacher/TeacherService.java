package com.example.SchoolManagementSystem.Teacher;

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
    SchoolService schoolService;

    @Autowired
    SubjectService subjectService;

    private static final Logger logger = LoggerFactory.getLogger(TeacherService.class);

    public Teacher createTeacher(NewTeacherDto newTeacherDto) throws UnirestException {
        //TODO Validate the subjects coming from the request body using annotation
        School school = schoolService.findSchoolById(newTeacherDto.getSchoolId());

        Teacher newTeacher = Teacher.builder()
                .staffId(UUID.randomUUID().toString().substring(0, 5))
                .school(school)
                .build();

        List<UUID> subjectIds = newTeacherDto.getSubjectId();
        List<Subject> subjects = new ArrayList<>();

        subjectIds.forEach(subjectId -> {
            Subject subject = subjectService.findSubjectById(subjectId);

            Teacher subjectPresent = teacherRepository.findSubjectInTeacher(subjectId, newTeacher.getId());
            if (subjectPresent != null) throw new IllegalStateException("Subject already assigned teacher");

            subjects.add(subject);
        });

        newTeacher.setSubjects(subjects);

        User user = userService.createUser(newTeacherDto);
        newTeacher.setUser(user);

        return teacherRepository.save(newTeacher);
    }

    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }

//    public Teacher getTeacher(UUID id) {
//        Optional<Teacher> teacherOptional = teacherRepository.findById(id);
//        if (teacherOptional.isPresent()) return teacherOptional.get();
//        throw new IllegalStateException("teacher not found");
//    }

    public Teacher findTeacherById(UUID id) {
        return teacherRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Teacher not found on :: " + id));
    }

    public Object updateTeacher(UpdateTeacherDto updateTeacherDto, UUID id) {
        Teacher teacherOptional = teacherRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("teacher not found on :: " + id));

        List<Subject> teacherSubjects = teacherOptional.getSubjects();

        if (updateTeacherDto.getSubjectId() != null || updateTeacherDto.getSubjectId().size() > 0) {
            List<UUID> subjectIds = updateTeacherDto.getSubjectId();

            for (UUID subjectId : subjectIds) {
                Subject subject = subjectService.findSubjectById(subjectId);

                Teacher subjectPresent = teacherRepository.findSubjectInTeacher(subjectId, teacherOptional.getId());
                if (subjectPresent != null) throw new IllegalStateException("Subject already assigned to teacher");

                teacherSubjects.add(subject);
            }
        }
        teacherOptional.setSubjects(teacherSubjects);

        return teacherRepository.save(teacherOptional);
    }

    public String deleteTeacher(UUID id) {
        Teacher teacher = teacherRepository.findById(id).orElseThrow(() -> new IllegalStateException("Teacher not found on :: " + id));
        teacherRepository.delete(teacher);

        logger.info("User deleted successfully");

        return "deleted successfully";
    }
}
