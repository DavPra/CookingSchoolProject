package Cooking.School.Project.cookingSchool.controller;

import Cooking.School.Project.cookingSchool.Services.UserService;
import Cooking.School.Project.cookingSchool.entities.Recipe;
import Cooking.School.Project.cookingSchool.entities.User;
import Cooking.School.Project.cookingSchool.exceptions.CourseNotFoundException;
import Cooking.School.Project.cookingSchool.exceptions.EntityNotFoundException;
import Cooking.School.Project.cookingSchool.exceptions.UserNotFoundException;
import Cooking.School.Project.cookingSchool.Services.CourseService;
import Cooking.School.Project.cookingSchool.Services.RecipeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private RecipeService recipeService;


    //TEST

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id){
        try {
            User user = userService.getUserById(id);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (UserNotFoundException unfe) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable Long id){
        try {
            userService.deleteUserById(id);
            return new ResponseEntity<>("User deleted successfully", HttpStatus.OK);
        } catch (UserNotFoundException unfe) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Transactional
    @PutMapping("/users")
    public ResponseEntity<?> updateUser(@RequestBody User user){
        try {
            userService.updateUser(user);
            return new ResponseEntity<>("User updated successfully", HttpStatus.OK);
        } catch (UserNotFoundException unfe) {
            return new ResponseEntity<>(unfe.getMessage(), HttpStatus.NOT_FOUND);
        }

    }

    @Transactional
    @PostMapping("/users/{userId}/book-course/{courseId}")
    public ResponseEntity<?> bookCourse(@PathVariable Long userId, @PathVariable Long courseId) {
        try {
            userService.bookCourse(userId, courseId);
            return ResponseEntity.ok("Course booked successfully");
        } catch (UserNotFoundException unfe) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        } catch(CourseNotFoundException cnfe){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Course not found");
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
        }
    }

    @Transactional
    @PostMapping("/registration")
    public ResponseEntity<?> registration(@RequestBody User user){
        try {
            userService.registration(user);
            return ResponseEntity.ok("successfully registered");
        }catch (DuplicateKeyException dke){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email address already registered");
        }
    }

    @GetMapping("/users/recipes/{id}")
    public ResponseEntity<?> getRecipesByID(@PathVariable Long id){
        try {
            Recipe recipe = recipeService.getRecipeById(id);
            return new ResponseEntity<>(recipe, HttpStatus.OK);
        } catch (UserNotFoundException unfe) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
