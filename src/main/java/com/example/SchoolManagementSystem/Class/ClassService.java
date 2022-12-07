package com.example.SchoolManagementSystem.Class;

import com.example.SchoolManagementSystem.Class.Dto.NewClassDto;
import com.example.SchoolManagementSystem.Class.Dto.UpdateClassDto;
import com.example.SchoolManagementSystem.School.School;
import com.example.SchoolManagementSystem.School.SchoolService;
import com.example.SchoolManagementSystem.Teacher.Teacher;
import com.example.SchoolManagementSystem.Teacher.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

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

    public Class getClass(UUID id) {
        return findClassById(id);
    }

    public List<Class> getClassBySchoolId(UUID schoolId) {
        schoolService.findSchoolById(schoolId);

        return classRepository.findClassBySchoolId(schoolId);
    }

    public Class findClassById(UUID id) {
        return classRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("class not found"));
    }

    public Class updateClass(UpdateClassDto updateClassDto, UUID id) {
        Class classOptional = findClassById(id);

        Teacher teacher = updateClassDto.getTeacherId() != null ?
                teacherService.findTeacherById(updateClassDto.getTeacherId()) : null;

        classOptional.setDepartment(updateClassDto.getDepartment());
        classOptional.setName(updateClassDto.getName());
        classOptional.setGrade(updateClassDto.getGrade());
        classOptional.setTeacher(teacher);

        return classRepository.save(classOptional);
    }

    public String deleteClass(UUID id) {
        Class classOptional = findClassById(id);

        classRepository.delete(classOptional);
        return "deleted successfully";
    }
}
