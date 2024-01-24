package cydeo.controller;

import cydeo.dto.ResponseWrapper;
import cydeo.dto.TeacherDTO;
import cydeo.service.ParentService;
import cydeo.service.StudentService;
import cydeo.service.TeacherService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController

public class SchoolController {

    private final TeacherService teacherService;
    private final StudentService studentService;
    private final ParentService parentService;

    public SchoolController(TeacherService teacherService, StudentService studentService, ParentService parentService) {
        this.teacherService = teacherService;
        this.studentService = studentService;
        this.parentService = parentService;
    }

    //write a method for teachers and returns as a list of teachers

    @GetMapping("/teachers")
    public List<TeacherDTO> readAllTeachers(){
      List<TeacherDTO>  teachers = teacherService.findAll();
      return  teachers;
    }

    /* create an endpoint for students, where JSON includes
    "Students are successfully retrieved"
    code: 200
    success: true
    and student date
     */
    @GetMapping("/students")
    public ResponseEntity<ResponseWrapper>readAllStudents(){
        return ResponseEntity.ok( new ResponseWrapper("Students are successfully retrieved"
                , studentService.findAll()));
    }

    /*
    create a parents' endpoint where status code is 202(ACCEPTED)
    additional header has "Parent" , and "Returned"
    and following json body structure
    "Parents are successfully retrieved"
    code: 200
    success: true
    and student date
     */
    @GetMapping("/parents")
    public ResponseEntity<ResponseWrapper> readAllParents(){
        // preparing my responseWrapper object with allArgsConstructor
        ResponseWrapper responseWrapper = new ResponseWrapper(true, "Parents are successfully retrieved"
                , HttpStatus.ACCEPTED.value(), parentService.findAll()) ;
        return ResponseEntity
                .status(HttpStatus.ACCEPTED) // new status code
                .header("Parent", "Returned") // new header
                .body(responseWrapper); // new designed response JSON Body
    }



}
