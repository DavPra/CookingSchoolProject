package Cooking.School.Project.cookingSchool.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST,  reason = "Primary ID is null or empty")
    public class PrimaryIdNullOrEmptyException extends RuntimeException {

        public PrimaryIdNullOrEmptyException() {
            super("Primary ID is null or empty");
        }
    }