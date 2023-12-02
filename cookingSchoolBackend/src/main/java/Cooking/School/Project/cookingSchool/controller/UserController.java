package Cooking.School.Project.cookingSchool.controller;

import Cooking.School.Project.cookingSchool.Services.UserService;
import Cooking.School.Project.cookingSchool.entities.User;
import Cooking.School.Project.cookingSchool.exceptions.CourseNotFoundException;
import Cooking.School.Project.cookingSchool.exceptions.EntityNotFoundException;
import Cooking.School.Project.cookingSchool.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserService userService;



    //TEST
    @PostMapping("/users")
    public User addUser(@RequestBody User user){
        return  userService.addUser(user);
    }

    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable Long id){
        return userService.getUserById(id);
    }

    @DeleteMapping("/users/{id}")
    public void deleteUserById(@PathVariable Long id){
        userService.deleteUserById(id);
    }

    @PutMapping("/users")
    public User updateUser(@RequestBody User user){
        return userService.updateUser(user);
    }

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


}
