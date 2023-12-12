package Cooking.School.Project.cookingSchool.exceptions;

public class MaxAttendantsReachedException extends RuntimeException {

    public MaxAttendantsReachedException(int maxNumberOfAttendants) {
        super(String.format("The maximum number of attendants %d was reached!", maxNumberOfAttendants));
    }
}
