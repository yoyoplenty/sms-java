package com.example.SchoolManagementSystem.School;

import com.example.SchoolManagementSystem.Address.Address;
import com.example.SchoolManagementSystem.Address.AddressService;
import com.example.SchoolManagementSystem.Address.Dto.NewAddressDto;
import com.example.SchoolManagementSystem.School.Dto.NewSchoolDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        List<NewAddressDto> addresses = newSchoolDto.getAddress();
        List<Address> savedAddress = new ArrayList<>();

        addresses.forEach(address -> {
            Address newAddress = addressService.createAddress(address);
            savedAddress.add(newAddress);
        });

        School school = School.builder()
                .email(newSchoolDto.getEmail())
                .locked(false)
                .name(newSchoolDto.getName())
                .address(savedAddress)
                .build();

        return schoolRepository.save(school);
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
        return schoolRepository.findSchoolByEmail(email);
    }

    public School findSchoolByName(String name) {
        return schoolRepository.findSchoolByName(name);
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
