package Cooking.School.Project.cookingSchool.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "Duplicate key violation")
public class DuplicateKeyException extends RuntimeException {

    public DuplicateKeyException() {
        super("Duplicate key violation");
    }

    public DuplicateKeyException(String message){
        super(message);
    }


}
