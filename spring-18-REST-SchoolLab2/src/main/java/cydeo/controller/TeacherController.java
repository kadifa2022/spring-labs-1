package cydeo.controller;

import cydeo.dto.ResponseWrapper;
import cydeo.dto.TeacherDTO;
import cydeo.service.TeacherService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController

public class TeacherController {

    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping("/teachers")
    public List<TeacherDTO> readAllTeachers(){
      List<TeacherDTO>  teachers = teacherService.findAll();
      return  teachers;
    }


}
