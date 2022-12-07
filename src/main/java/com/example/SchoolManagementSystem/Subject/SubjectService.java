package com.example.SchoolManagementSystem.Subject;

import com.example.SchoolManagementSystem.School.School;
import com.example.SchoolManagementSystem.School.SchoolService;
import com.example.SchoolManagementSystem.Subject.Dto.NewSubjectDto;
import com.example.SchoolManagementSystem.Subject.Dto.UpdateSubjectDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SubjectService {

    @Autowired
    SubjectRepository subjectRepository;

    @Autowired
    SchoolService schoolService;


    public Subject createSubject(NewSubjectDto newSubjectDto) {
        Optional<Subject> subject = subjectRepository.findSubjectByNameAndGrade(newSubjectDto.getName(), newSubjectDto.getGrade());
        if (subject.isPresent()) throw new IllegalStateException("Subject already exists");

        School school = schoolService.findSchoolById(newSubjectDto.getSchoolId());

        Subject newSubject = Subject.builder()
                .name(newSubjectDto.getName())
                .code(newSubjectDto.getCode())
                .grade(newSubjectDto.getGrade())
                .school(school)
                .build();

        return subjectRepository.save(newSubject);
    }

    public List<Subject> getAllSubject() {
        return subjectRepository.findAll();
    }

    public Subject getSubject(UUID id) {
        return findSubjectById(id);
    }

    public List<Subject> getSubjectsBySchoolId(UUID schoolId) {
        schoolService.findSchoolById(schoolId);

        return subjectRepository.findSubjectBySchoolId(schoolId);
    }

    public Subject findSubjectById(UUID id) {
        return subjectRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Subject not found on :: " + id));
    }

    public Subject updateSubject(UpdateSubjectDto updateSubjectDto, UUID id) {
        Subject subject = findSubjectById(id);

        subject.setName(updateSubjectDto.getName());
        subject.setCode(updateSubjectDto.getCode());
        subject.setGrade(updateSubjectDto.getGrade());

        return subjectRepository.save(subject);
    }

    public String deleteSubject(UUID id) {
        Subject subject = findSubjectById(id);
        subjectRepository.delete(subject);

        return "deleted successfully";
    }
}
