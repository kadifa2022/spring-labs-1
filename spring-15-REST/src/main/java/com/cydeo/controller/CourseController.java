package com.cydeo.controller;

import com.cydeo.dto.CourseDTO;
import com.cydeo.service.CourseService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController  //@controller and @ResponseBody
@RequestMapping("/courses/api/v1")//best practice to create endpoints
public class CourseController {
    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }


    @GetMapping
   // @ResponseBody//no view anymore//
    public List<CourseDTO> getAllCourses(){

        return courseService.getCourses();

    }
    @GetMapping("{id}")
    public CourseDTO GetCourseById(@PathVariable("id") Long  courseId){

        return courseService.getCourseById(courseId);

    }
    @GetMapping("category/{name}")
    public List<CourseDTO> getCourseByCategory(@PathVariable("name") String category){
        return courseService.getCoursesByCategory(category);
    }
    @PostMapping
    public CourseDTO createCourse(@RequestBody CourseDTO course){
        return courseService.createCourse(course);

    }
    @PutMapping("{id}")
    public void updateCourse(@PathVariable("id")Long courseId,@RequestBody CourseDTO course ){
        courseService.updateCourse(courseId, course);


    }
    @DeleteMapping("{id}")
    public void deleteCourseById(@PathVariable("id") Long courseId) {
        courseService.deleteCourseById(courseId);

    }
}
