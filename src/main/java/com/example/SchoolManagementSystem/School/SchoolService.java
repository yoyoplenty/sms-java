package com.example.SchoolManagementSystem.School;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class SchoolService {

    @Autowired
    SchoolRepository schoolRepository;

//    public UserService(UserRepository userRepository) { this.userRepository = userRepository; }

    public School createSchool(School school) {
        Optional<School> schoolOptional = schoolRepository.findSchoolBySchoolEmail(school.getSchoolEmail());
        if (schoolOptional.isPresent()) throw new IllegalStateException("School already exists");

        return schoolRepository.save(school);
    }

    public List<School> getAllSchools() {
        System.out.println("i am okay");
        return schoolRepository.findAll();
    }

    public School getSchool(int id) {
        Optional<School> schoolOptional = schoolRepository.findById(id);
        if (schoolOptional.isPresent()) return schoolOptional.get();
        throw new IllegalStateException("user not found");
    }

    public School updateSchool(School school, int id) {

        School schoolOptional = schoolRepository.findById(id).orElseThrow(() -> new IllegalStateException("school not found on :: " + id));
//        userOptional.setFirstName(user.getFirstName());
//        userOptional.setLastName(user.getLastName());
//        userOptional.setPhoneNumber(user.getPhoneNumber());
//        userOptional.setEmail(user.getEmail());

        return schoolRepository.save(schoolOptional);
    }

    public String deleteSchool(int id) {
        School school = schoolRepository.findById(id).orElseThrow(() -> new IllegalStateException("school not found on :: " + id));
        schoolRepository.delete(school);
        return "deleted successfully";
    }
}
