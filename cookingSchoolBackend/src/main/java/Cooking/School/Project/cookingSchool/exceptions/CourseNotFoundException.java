package Cooking.School.Project.cookingSchool.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "course not found")
public class CourseNotFoundException extends RuntimeException {
    private Long courseId;

    public CourseNotFoundException(Long userId) {
        super("User not found with Id" + userId);
        this.courseId = courseId;
    }

    public Long getUserId(){
        return courseId;
    }
}
