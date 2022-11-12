//package com.example.SchoolManagementSystem.Utils.Base;
//
//import org.springframework.beans.factory.annotation.Autowired;
//
//public class BaseService<Model, BaseRepository, CreateDto, UpdateDto> {
//
//    @Autowired
//    BaseRepository baseRepository;
//
//    public Model create(CreateDto createDto) {
//        Model model = new Model(createDto);
//        return baseRepository.save();
//    }
//
//
//}
