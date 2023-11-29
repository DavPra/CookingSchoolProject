package Cooking.School.Project.cookingSchool.controller;

import Cooking.School.Project.cookingSchool.Services.*;
import Cooking.School.Project.cookingSchool.entities.*;
import Cooking.School.Project.cookingSchool.exceptions.*;
import Cooking.School.Project.cookingSchool.restapi.DTO.CourseInputParam;

import Cooking.School.Project.cookingSchool.restapi.DTO.CourseRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AdminController {

    @Autowired
   private CourseService courseService;

    @Autowired
    private UserService userService;

    @Autowired
    private TagService tagService;

    @Autowired
    private RecipeService recipeService;

    @Autowired
    private RatingService ratingService;

//-------------------------------- Admin course
    @PostMapping("admin/courses")
    public ResponseEntity<?> createCourse(@RequestBody CourseRequest request) {
        try {
            Long courseId = courseService.createCourse(request);
            return ResponseEntity.ok("Kurs erfolgreich erstellt "+courseId);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("nonono");
        }
    }


    @GetMapping("admin/courses")
    public ResponseEntity<List<Course>> getAllCourses(){
        List<Course> courses = courseService.getAllCourses();
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }

    @DeleteMapping("admin/courses/{id}")
    public ResponseEntity<?> deleteCourse(@PathVariable Long id){
        try{
            courseService.deleteCourseById(id);
            return new ResponseEntity<>("Kurs erfolgreich gelöscht", HttpStatus.OK);
        } catch (PrimaryIdNullOrEmptyException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("admin/courses/{id}")
    public ResponseEntity<?> updateCourse(@PathVariable Long id, @RequestBody CourseInputParam param){
        try{
            courseService.updateCourseById(id, param);
            return new ResponseEntity<>("Kurs erfolgreich aktualisiert", HttpStatus.OK);
        } catch (PrimaryIdNullOrEmptyException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    //------------------------- Admin tags

    @PostMapping("admin/courseTag")
    public CourseTag addCourseTag(@RequestBody CourseTag courseTag){
        return tagService.addCourseTag(courseTag);
    }
    @GetMapping("admin/courseTag")
    public ResponseEntity<List<CourseTag>> getAllCourseTags(){
        List<CourseTag> courseTags = tagService.getAllCourseTags();
        return new ResponseEntity<>(courseTags, HttpStatus.OK);
    }

    //--------------------------- Admin users administration

    @GetMapping("/admin/users")
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping("/admin/users")
    public ResponseEntity<?> addUser(@RequestBody User user){
        try{
            userService.addUser(user);
            return new ResponseEntity<>("User erfolgreich erstellt", HttpStatus.CREATED);
        } catch (DuplicateKeyException dke){
            return new ResponseEntity<>(dke.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/admin/users/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable Long id){
        try{
            userService.deleteUserById(id);
            return new ResponseEntity<>("User erfolgreich gelöscht", HttpStatus.OK);
        } catch (PrimaryIdNullOrEmptyException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/admin/users/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody User user){
        try{
            userService.updateUser(user);
            return new ResponseEntity<>("User erfolgreich aktualisiert", HttpStatus.OK);
        } catch (PrimaryIdNullOrEmptyException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/admin/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id){
        User user = userService.getUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }


    //--------------------------- Admin ratings administration

    @GetMapping("/admin/ratings")
    public ResponseEntity<List<Rating>> getAllRatings(){
        List<Rating> ratings = ratingService.getAllRatings();
        return new ResponseEntity<>(ratings, HttpStatus.OK);
    }

    @PostMapping("/admin/ratings")
    public ResponseEntity<?> addRating(@RequestBody Rating rating){
        try{
            ratingService.addRating(rating);
            return new ResponseEntity<>("Bewertung erfolgreich erstellt", HttpStatus.CREATED);
        } catch (DuplicateKeyException dke){
            return new ResponseEntity<>(dke.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/admin/ratings/{id}")

    public ResponseEntity<?> deleteRatingById(@PathVariable Long id){
        try{
            ratingService.deleteRatingById(id);
            return new ResponseEntity<>("Bewertung erfolgreich gelöscht", HttpStatus.OK);
        } catch (PrimaryIdNullOrEmptyException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/admin/ratings/{id}")

    public ResponseEntity<?> updateRating(@PathVariable Long id, @RequestBody Rating rating){
        try{
            ratingService.updateRating(rating);
            return new ResponseEntity<>("Bewertung erfolgreich aktualisiert", HttpStatus.OK);
        } catch (PrimaryIdNullOrEmptyException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


}


