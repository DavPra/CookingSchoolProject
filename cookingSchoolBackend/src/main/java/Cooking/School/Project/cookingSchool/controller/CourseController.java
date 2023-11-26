package Cooking.School.Project.cookingSchool.controller;

import Cooking.School.Project.cookingSchool.Services.CourseService;
import Cooking.School.Project.cookingSchool.entities.Course;
import Cooking.School.Project.cookingSchool.entities.CourseTag;
import Cooking.School.Project.cookingSchool.repository.CourseTagRepository;
import Cooking.School.Project.cookingSchool.restapi.inputParams.CourseRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
public class CourseController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private CourseTagRepository courseTagRepository;


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
