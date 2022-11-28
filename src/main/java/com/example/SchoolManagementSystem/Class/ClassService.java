package com.example.SchoolManagementSystem.Class;

import com.example.SchoolManagementSystem.Class.Dto.NewClassDto;
import com.example.SchoolManagementSystem.School.School;
import com.example.SchoolManagementSystem.School.SchoolService;
import com.example.SchoolManagementSystem.Teacher.Teacher;
import com.example.SchoolManagementSystem.Teacher.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassService {

    @Autowired
    ClassRepository classRepository;

    @Autowired
    SchoolService schoolService;

    @Autowired
    TeacherService teacherService;

    public Class createClass(NewClassDto newClassDto) {
        School school = schoolService.findSchoolById(newClassDto.getSchoolId());

        Teacher teacher = newClassDto.getTeacherId() != null ?
                teacherService.findTeacherById(newClassDto.getTeacherId()) : null;

        Class newClass = Class.builder()
                .name(newClassDto.getName())
                .grade(newClassDto.getGrade())
                .department(newClassDto.getDepartment())
                .school(school)
                .teacher(teacher)
                .build();

        return classRepository.save(newClass);
    }

    public List<Class> getClasses() {
        return classRepository.findAll();
    }
}
