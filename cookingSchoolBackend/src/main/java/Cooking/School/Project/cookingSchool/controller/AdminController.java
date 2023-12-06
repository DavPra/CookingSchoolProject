package Cooking.School.Project.cookingSchool.controller;

import Cooking.School.Project.cookingSchool.Services.*;
import Cooking.School.Project.cookingSchool.entities.*;
import Cooking.School.Project.cookingSchool.exceptions.*;

import Cooking.School.Project.cookingSchool.restapi.DTO.CourseRequest;
import Cooking.School.Project.cookingSchool.restapi.DTO.CourseTagsRecipeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

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
    @Transactional
    @PostMapping("admin/courses")
    public ResponseEntity<?> createCourse(@RequestBody CourseRequest request) {
        try {
            Long courseId = courseService.createCourse(request);
            return ResponseEntity.ok("Kurs erfolgreich erstellt "+courseId);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("nonono");
        }
    }

//TODO Retten weil JSON Ignore ...da weiter 4.12.
    @GetMapping("admin/courses")
    public ResponseEntity<List<CourseTagsRecipeResponse>> getAllCourses(){
        try{
            List<CourseTagsRecipeResponse> courseTagsRecipeResponses = courseService.getAllCourses();
            return new ResponseEntity<>(courseTagsRecipeResponses,HttpStatus.OK );
        }catch (CourseNotFoundException cnfe){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

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

    /**
     * updatet einen Kurs inkl zugehöriger Tags
     * @param courseId
     * @param param
     * @return
     */

    @Transactional
    @PutMapping("admin/courses/{courseId}")
    public ResponseEntity<?> updateCourse(@PathVariable Long courseId, @RequestBody CourseRequest param){
        try {
            Set<CourseTag> courseTags = param.getCourseTags();

            Course updatedCourse = courseService.updateCourse(courseId, param.getCourseTitle(),
                    param.getDescription(), param.getTeacher(), param.getStartDate(),
                    param.getMaxAttendants(), param.getPrice(), courseTags);
            return new ResponseEntity<>("Kurs erfolgreich aktualisiert", HttpStatus.OK);

        } catch (PrimaryIdNullOrEmptyException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (InvalidStartDateException isde){
            return new ResponseEntity<>(isde.getMessage(), HttpStatus.NOT_FOUND);
        }catch (TagNotFoundException tnfe){
            return new ResponseEntity<>(tnfe.getMessage(), HttpStatus.NOT_FOUND);
        }

    }

    //------------------------- Admin tags

    @Transactional
    @PostMapping("admin/courseTag")
    public ResponseEntity<?> addCourseTag(@RequestBody CourseTag courseTag){
        try{
            tagService.addCourseTag(courseTag);
            return new ResponseEntity<>("Tag erfolgreich erstellt", HttpStatus.CREATED);
        } catch (DuplicateKeyException dke){
            return new ResponseEntity<>(dke.getMessage(), HttpStatus.BAD_REQUEST);
        }
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

    @Transactional
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


}


