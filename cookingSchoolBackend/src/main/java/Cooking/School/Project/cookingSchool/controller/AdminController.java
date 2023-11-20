package Cooking.School.Project.cookingSchool.controller;

import Cooking.School.Project.cookingSchool.Services.CourseService;
import Cooking.School.Project.cookingSchool.entities.Course;
import Cooking.School.Project.cookingSchool.exceptions.PrimaryIdNullOrEmptyException;
import Cooking.School.Project.cookingSchool.restapi.inputParams.CourseInputParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AdminController {

    @Autowired
   private CourseService courseService;


    @PostMapping("admin/courses")
    public ResponseEntity<?> createCourse (@RequestBody CourseInputParam param){
        try{
           boolean createdCourse =  courseService.createCourse(
                   param.getCourseId(),
                   param.getTitle(),
                   param.getDescription(),
                   param.getTeacher(),
                   param.getDate()
           );
            return new ResponseEntity<>("Kurs erfolgreich erstellt", HttpStatus.CREATED);
        } catch (PrimaryIdNullOrEmptyException e ){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("admin/courses")
    public ResponseEntity<List<Course>> getAllCourses(){
        List<Course> courses = courseService.getAllCourses();
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }


}
