package Cooking.School.Project.cookingSchool.controller;

import Cooking.School.Project.cookingSchool.Services.*;
import Cooking.School.Project.cookingSchool.entities.*;
import Cooking.School.Project.cookingSchool.exceptions.*;

import Cooking.School.Project.cookingSchool.restapi.dto.CourseRequest;
import Cooking.School.Project.cookingSchool.restapi.dto.CourseTagsRecipeResponse;
import Cooking.School.Project.cookingSchool.restapi.dto.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@PreAuthorize("hasAuthority('ADMIN')")
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

//-------------------------------- Admin course

    /**
     * POST method to create a new course in the admin panel
     * @param request containing the information for the course to be created
     * @return Http Status 200 OK and a success message or 500 BadRequest and a message
     */
    //TODO required fields?
    @PostMapping("admin/courses")
    public ResponseEntity<?> createCourse(@RequestBody CourseRequest request) {
        try {
            Long courseId = courseService.createCourse(request);
            return ResponseEntity.ok("course successfully created  "+courseId);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("nonono");
         }catch (TagNotFoundException tnfe){
        return new ResponseEntity<>(tnfe.getMessage(), HttpStatus.NOT_FOUND);
         }
    }

    /**
     * GET method to get a list of all courses including tags and recipes in th admin dashboard
     * @return HttpStatus 200 created or 404 not found
     */
    @GetMapping("admin/courses")
    public ResponseEntity<List<CourseTagsRecipeResponse>> getAllCourses(){
        try{
            List<CourseTagsRecipeResponse> courseTagsRecipeResponses = courseService.getAllCourses();
            return new ResponseEntity<>(courseTagsRecipeResponses,HttpStatus.CREATED );
        }catch (CourseNotFoundException cnfe){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * GET Methode to get a Course by id
     * @param courseId
     * @return course and HttpStatus 200 created or 404 not found
     */
    @GetMapping("admin/courses/{courseId}")
    public ResponseEntity<?> getCourseById(@PathVariable Long courseId){
        Course course = courseService.getCourseById(courseId);
        return new ResponseEntity<>(course, HttpStatus.OK);
    }

    /**
     * DELETE Methode to delete a course by id
     * @param id the course id
     * @return a success message or 404 not found
     */
//TODO Reza fragen wegen BAD REquest abfangen
    @DeleteMapping("admin/courses/{id}")
    public ResponseEntity<?> deleteCourse(@PathVariable Long id){
        try{
            courseService.deleteCourseById(id);
            return new ResponseEntity<>("Kurs erfolgreich gelöscht", HttpStatus.OK);
        } catch (PrimaryIdNullOrEmptyException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * PUT Method to update a Course by id
     * @param courseId
     * @param param updated Course data
     * @return HttpStatus 200 ok and a success message, 409 conflict when
     * start date is in the past, 404 not found when course tag or course not found
     */

    @PutMapping("admin/courses/{courseId}")
    public ResponseEntity<?> updateCourse(@PathVariable Long courseId, @RequestBody CourseRequest param){
            Set<CourseTag> courseTags = param.getCourseTags();

            Course updatedCourse = courseService.updateCourse(courseId, param.getCourseTitle(),
                    param.getDescription() ,param.getTeacher(),param.getImage(), param.getStartDate(),
                    param.getMaxAttendants(), param.getPrice(), courseTags);
            return new ResponseEntity<>("Kurs erfolgreich aktualisiert", HttpStatus.OK);

    }

    //------------------------- Admin tags

    /**
     * POST Method to create a new course tag
     * @param courseTag Title
     * @return HttpStatus 201 created and a success message
     */
    @Transactional
    @PostMapping("admin/courseTag")
    public ResponseEntity<?> addCourseTag(@RequestBody CourseTag courseTag) {
        tagService.addCourseTag(courseTag);
        return new ResponseEntity<>("Tag erfolgreich erstellt", HttpStatus.CREATED);
    }

    /**
     * GET Method to get all course tags
     * @return a list of course tags , HttpStatus 200 ok
     */

    @GetMapping("admin/courseTag")
    public ResponseEntity<List<CourseTag>> getAllCourseTags(){
        List<CourseTag> courseTags = tagService.getAllCourseTags();
        return new ResponseEntity<>(courseTags, HttpStatus.OK);
    }

    //--------------------------- Admin users administration


    //TODO da weiter
    /**
     * GET Method to get all Users
     * @return a List of the UserRsponse
     */
    @GetMapping("/admin/users")
    public ResponseEntity<List<UserResponse>> getAllUsers(){
        List<User> users = userService.getAllUsers();
        List<UserResponse> userResponses = users.stream()
                .map(UserResponse::fromUser)
                .collect(Collectors.toList());
        return new ResponseEntity<>(userResponses, HttpStatus.OK);
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

    @Transactional
    @PutMapping("admin/users/{userId}")
    public ResponseEntity<?> updateUser(
            @PathVariable Long userId, @RequestBody User updatedUser){
        try {
            userService.updateUser(userId, updatedUser);
            return new ResponseEntity<>("User updated successfully", HttpStatus.OK);
        } catch (UserNotFoundException unfe) {
            return new ResponseEntity<>(unfe.getMessage(), HttpStatus.NOT_FOUND);
        }

    }

    @Transactional
    @PutMapping("admin/users/editUser")
    public ResponseEntity<?> editUser(@RequestParam Long userId, @RequestBody User updatedUser) {
        try {
            userService.editUser(userId, updatedUser);
            return new ResponseEntity<>("User updated successfully", HttpStatus.OK);
        } catch (UserNotFoundException unfe) {
            return new ResponseEntity<>(unfe.getMessage(), HttpStatus.NOT_FOUND);
        } catch (PrimaryIdNullOrEmptyException pe) {
            return new ResponseEntity<>(pe.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/admin/users/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long id){
        UserResponse userResponse = userService.getUserById(id);
        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }
    @PostMapping("/admin/{userId}/book-course/{courseId}")
    public ResponseEntity<?> bookCourse(@PathVariable Long userId, @PathVariable Long courseId) {
        userService.bookCourse(userId, courseId);
        return ResponseEntity.ok("Course booked successfully");
    }



}


