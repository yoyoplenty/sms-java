package com.example.SchoolManagementSystem.School;

import com.example.SchoolManagementSystem.Address.Address;
import com.example.SchoolManagementSystem.Address.AddressService;
import com.example.SchoolManagementSystem.Address.Dto.NewAddressDto;
import com.example.SchoolManagementSystem.School.Dto.NewSchoolDto;
import com.example.SchoolManagementSystem.School.Dto.UpdateSchoolDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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
        return findSchoolById(id);
    }

    public School findSchoolByEmail(String email) {
        return schoolRepository.findSchoolByEmail(email);
    }

    public School findSchoolByName(String name) {
        return schoolRepository.findSchoolByName(name);
    }

    public School findSchoolById(UUID id) {
        return schoolRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("School Not Found"));
    }

    public School findSchoolByAddressId(UUID addressId) {
        return schoolRepository.findSchoolByAddressId(addressId)
                .orElseThrow(() -> new IllegalStateException("School Not Found"));
    }

    public School getSchoolById(UUID id) {
        return schoolRepository.findSchoolById(id);
    }

    public School updateSchool(UpdateSchoolDto updateSchoolDto, UUID id) {
        School school = findSchoolById(id);
        List<Address> savedAddress = school.getAddress();

        if (updateSchoolDto.getAddress() != null) {
            Address newAddress = addressService.createAddress(updateSchoolDto.getAddress());
            savedAddress.add(newAddress);
        }

        school.setName(school.getName());
        school.setLocked(school.getLocked());
        school.setAddress(savedAddress);

        return schoolRepository.save(school);
    }

    public School deleteAddressInSchool(UUID addressId) {
        School school = findSchoolByAddressId(addressId);

        addressService.deleteAddress(addressId);
        return school;
    }

    public String deleteSchool(UUID id) {
        School school = findSchoolById(id);

        schoolRepository.delete(school);
        return "deleted successfully";
    }
}
