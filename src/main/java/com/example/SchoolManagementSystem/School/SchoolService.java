package com.example.SchoolManagementSystem.School;

import com.example.SchoolManagementSystem.Address.Address;
import com.example.SchoolManagementSystem.Address.AddressService;
import com.example.SchoolManagementSystem.School.Dto.NewSchoolDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class SchoolService {

    @Autowired
    SchoolRepository schoolRepository;

    @Autowired
    AddressService addressService;

    public School createSchool(NewSchoolDto newSchoolDto) {
        Optional<School> schoolOptional = schoolRepository.findSchoolByEmail(newSchoolDto.getEmail());
        if (schoolOptional.isPresent()) throw new IllegalStateException("School already exists");

        School school = School.builder()
                .email(newSchoolDto.getEmail())
                .locked(false).enabled(false)
                .name(newSchoolDto.getName()).build();

        School newSchool = schoolRepository.save(school);

        Address address = Address.builder()
                .street(newSchoolDto.getStreet()).lga(newSchoolDto.getLga())
                .state(newSchoolDto.getState()).contactPersonName(newSchoolDto.getContactPersonName())
                .contactPersonEmail(newSchoolDto.getContactPersonEmail())
                .school(newSchool).build();

        addressService.createAddress(address);
        return newSchool;
    }

    public List<School> getAllSchools() {
        return schoolRepository.findAll();
    }

    public School getSchool(UUID id) {
        Optional<School> schoolOptional = schoolRepository.findById(id);
        if (schoolOptional.isPresent()) return schoolOptional.get();
        throw new IllegalStateException("user not found");
    }

    public School findSchoolByEmail(String email) {
        return schoolRepository.findSchoolByEmail(email)
                .orElseThrow(() -> new IllegalStateException("School Not Found"));
    }

    public School findSchoolById(UUID id) {
        return schoolRepository.findSchoolById(id)
                .orElseThrow(() -> new IllegalStateException("School Not Found"));
    }

    public School updateSchool(School school, UUID id) {
        School schoolOptional = schoolRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("school not found on :: " + id));

        schoolOptional.setName(school.getName());
        schoolOptional.setLocked(school.getLocked());
        schoolOptional.setEnabled(school.getEnabled());
        schoolOptional.setAddress(school.getAddress());

        return schoolRepository.save(schoolOptional);
    }

    public String deleteSchool(UUID id) {
        School school = schoolRepository.findById(id).
                orElseThrow(() -> new IllegalStateException("school not found on :: " + id));

        schoolRepository.delete(school);
        return "deleted successfully";
    }
}
