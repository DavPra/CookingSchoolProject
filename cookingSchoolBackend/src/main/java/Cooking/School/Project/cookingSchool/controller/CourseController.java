package Cooking.School.Project.cookingSchool.controller;

import Cooking.School.Project.cookingSchool.Services.CourseService;
import Cooking.School.Project.cookingSchool.entities.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CourseController {

    @Autowired
    private CourseService courseService;

    @PostMapping("/courses")
    public Course addCourse(@RequestBody Course course){
        return  courseService.addCourse(course);
    }

    @GetMapping("/courses/{id}")
    public Course getCourseById(@PathVariable Long id){
        return courseService.getCourseById(id);
    }

    @DeleteMapping("/courses/{id}")
    public void deleteCourseById(@PathVariable Long id){
        courseService.deleteCourseById(id);
    }

    @PutMapping("/courses")
    public Course updateCourse(@RequestBody Course course){
        return courseService.updateCourse(course);
    }


}
