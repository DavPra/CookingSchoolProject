package Cooking.School.Project.cookingSchool.controller;

import Cooking.School.Project.cookingSchool.entities.Course;
import Cooking.School.Project.cookingSchool.entities.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Set;

/**
 *
 */
public class GetAllCoursesDTO {

    private CourseDTO course;
}


// WTF is this??
class CourseDTO extends Course {


    @JsonIgnore
    @Override
    public Set<User> getUsers() {
        return users;
    }



}
