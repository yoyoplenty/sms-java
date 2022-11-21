package com.example.SchoolManagementSystem.Subject;

import com.example.SchoolManagementSystem.Subject.Dto.NewSubjectDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SubjectService {

    @Autowired
    SubjectRepository subjectRepository;

    private static final Logger logger = LoggerFactory.getLogger(SubjectService.class);


    public Subject createSubject(NewSubjectDto newSubjectDto) {
        Optional<Subject> subject = subjectRepository.findSubjectByNameAndGrade(newSubjectDto.getName(), newSubjectDto.getGrade());
        if (subject.isPresent()) throw new IllegalStateException("Subject already exists");

        Subject newSubject = Subject.builder()
                .name(newSubjectDto.getName())
                .code(newSubjectDto.getCode())
                .grade(newSubjectDto.getGrade())
                .build();

        return subjectRepository.save(newSubject);
    }


    public Subject findSubjectById(UUID id) {
        Optional<Subject> optionalSubject = subjectRepository.findSubjectById(id);
        if (optionalSubject.isPresent()) return optionalSubject.get();

        throw new IllegalStateException("subject with id " + id + " not found");
    }

    public List<Subject> getAllSubject() {
        return subjectRepository.findAll();
    }
}
