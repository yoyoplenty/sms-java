package com.example.SchoolManagementSystem.Admin;

import com.example.SchoolManagementSystem.Admin.Dto.NewAdminDto;
import com.example.SchoolManagementSystem.School.School;
import com.example.SchoolManagementSystem.School.SchoolService;
import com.example.SchoolManagementSystem.Teacher.TeacherService;
import com.example.SchoolManagementSystem.Users.User;
import com.example.SchoolManagementSystem.Users.UserService;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AdminService {
    @Autowired
    AdminRepository adminRepository;

    @Autowired
    UserService userService;

    @Autowired
    SchoolService schoolService;

    private static final Logger logger = LoggerFactory.getLogger(TeacherService.class);

    public Admin createAdmin(NewAdminDto newAdminDto) throws UnirestException {
        School school = schoolService.findSchoolById(newAdminDto.getSchoolId());
        //TODO encrypt password before saving the user data from the admin.
        Admin newAdmin = Admin.builder()
                .staffId(UUID.randomUUID().toString().substring(0, 5))
                .school(school)
                .build();

        User user = userService.createUser(newAdminDto);
        newAdmin.setUser(user);

        return adminRepository.save(newAdmin);
    }

    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }

    public Admin findAdminById(UUID id) {
        return adminRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("admin not found on :: " + id));
    }
}
