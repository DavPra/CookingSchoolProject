package Cooking.School.Project.cookingSchool.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "User not found")
public class UserNotFoundException extends RuntimeException {
    private Long userId;


    public UserNotFoundException(Long courseId) {
        super("User not found with Id" + courseId);
        this.userId = courseId;
    }

    public UserNotFoundException(String message){
        super(message);
    }

    public Long getUserId(){
        return userId;
    }
}
