package Cooking.School.Project.cookingSchool.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No sufficient rights")
public class RightsException extends RuntimeException {

    public RightsException(String message){
        super(message);
    }


}
