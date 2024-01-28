package Cooking.School.Project.cookingSchool.exceptions;

public class InvalidStartDateException extends RuntimeException {

    private final String defaultMessage = "Deadline is not valid";

    public InvalidStartDateException () {
        super();
    }

    public InvalidStartDateException(String message) {
        super(message);
    }

    public InvalidStartDateException(String message, Long entityId){
        super(message);

    }

    public String getDefaultMessage(){
        return defaultMessage;
    }
}
