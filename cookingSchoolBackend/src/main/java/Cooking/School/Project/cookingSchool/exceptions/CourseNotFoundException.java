package Cooking.School.Project.cookingSchool.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "course not found")
public class CourseNotFoundException extends RuntimeException {
    private Long courseId;


    public CourseNotFoundException(Long courseId) {
        super("Course not found with Id" + courseId);
        this.courseId = courseId;
    }

    public CourseNotFoundException(String message){
        super(message);
    }
    public Long getUserId(){
        return courseId;
    }
}
