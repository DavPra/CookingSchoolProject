package Cooking.School.Project.cookingSchool.controller;

import Cooking.School.Project.cookingSchool.Services.CourseService;
import Cooking.School.Project.cookingSchool.Services.RecipeService;
import Cooking.School.Project.cookingSchool.Services.UserService;
import Cooking.School.Project.cookingSchool.entities.Course;
import Cooking.School.Project.cookingSchool.entities.Recipe;
import Cooking.School.Project.cookingSchool.entities.User;
import Cooking.School.Project.cookingSchool.exceptions.UserNotFoundException;
import Cooking.School.Project.cookingSchool.restapi.dto.CourseRequest;
import Cooking.School.Project.cookingSchool.restapi.dto.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    private CourseService courseService;

    private RecipeService recipeService;


    /**
     * Get Method to get a user by id
     *
     * @param id userId
     * @return User or 404 NotFound
     */
    @PreAuthorize("hasAnyAuthority('APPUSER')")
    @GetMapping("/users/{id}")
    public UserResponse getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    /**
     * Delete Method to delete a user from the database by id
     *
     * @param id usersId
     * @return HttpStatus 200 and a success message or 404 NotFound
     */

    @PreAuthorize("hasAnyAuthority('APPUSER')")
    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable Long id) {
        userService.deleteUserById(id);
        return new ResponseEntity<>("User deleted successfully", HttpStatus.OK);
    }

    /**
     * Put Method to update user data
     *
     * @param userId      in PathVariable
     * @param updatedUser updated user data
     * @return HttpStatus 200 ok and a success message, 404 NotFound or 500 BadRequest
     */
    @PreAuthorize("hasAnyAuthority('APPUSER')")
    @PutMapping("/users/{userId}")
    public ResponseEntity<?> updateUser(
            @PathVariable Long userId, @RequestBody User updatedUser) {
        userService.updateUser(userId, updatedUser);
        return new ResponseEntity<>("User updated successfully", HttpStatus.OK);

    }

    /**
     * Post Method to book a Course for users
     *
     * @param userId
     * @param courseId
     * @return 200 ok, 404 notfound when course id or user id is not found or 500 bad request when max Attendants reached
     */
    @PreAuthorize("hasAnyAuthority('APPUSER', 'ADMIN')")
    @PostMapping("/users/{userId}/book-course/{courseId}")
    public ResponseEntity<?> bookCourse(@PathVariable Long userId, @PathVariable Long courseId) {
        userService.bookCourse(userId, courseId);
        return ResponseEntity.ok("Course booked successfully");
    }


    /**
     * Post Method to register a user in the database
     *
     * @param user user data
     * @return HttpStatus 200 ok and a success message 409 conflict and an error message
     */
    @PostMapping("/registration")
    public ResponseEntity<?> registration(@RequestBody User user) {
        userService.registration(user);
        return ResponseEntity.ok("successfully registered");
    }

    /* doppelt
    @GetMapping("/courses/{courseId}")
    public ResponseEntity<Course> getUserCourseById(@PathVariable Long courseId) {
        Course course = courseService.getUserCourseById(courseId);
        return ResponseEntity.ok(course);
    }*/


}

