package Cooking.School.Project.cookingSchool.exceptions;

import Cooking.School.Project.cookingSchool.entities.Course;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.NoSuchElementException;

/**
 * Alle hier definierten handler müssen weder im Service(oben in einer methode) geworfen noch im Controller abgefangen werden.
 */
@ControllerAdvice
public class GlobalExceptionHandler{
    @ExceptionHandler(MaxAttendantsReachedException.class)
    public final ResponseEntity<?> handleMaxAttendantsReachedException(MaxAttendantsReachedException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(UserNotFoundException.class)
    public final ResponseEntity<ErrorResponse> handleUserNotFoundException(UserNotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }


    @ExceptionHandler(CourseNotFoundException.class)
    public final ResponseEntity<?> handleCourseNotFoundException(CourseNotFoundException ex) {
        System.out.println("hallo1");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(TagNotFoundException.class)
    public final ResponseEntity<?> handleTagNotFoundException(TagNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public final ResponseEntity<?> handleEntityNotFoundException(EntityNotFoundException ex) {
        System.out.println("hallo2");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
    @ExceptionHandler(RecipeNotFoundException.class)
    public final ResponseEntity<?> handleRecipeNotFoundException(RecipeNotFoundException ex) {
        System.out.println("hallo3");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(PrimaryIdNullOrEmptyException.class)
    public final ResponseEntity<ErrorResponse> handlePrimaryIdNullOrEmptyException(PrimaryIdNullOrEmptyException ex) {
        System.out.println("hallo4");
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }
    @ExceptionHandler(DuplicateKeyException.class)
    public final ResponseEntity<ErrorResponse> handleDuplicateKeyException(DuplicateKeyException ex) {
        System.out.println("hallo5");
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.CONFLICT.value(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }
    @ExceptionHandler(InvalidStartDateException.class)
    public final ResponseEntity<ErrorResponse> handleInvalidStartDateException(InvalidStartDateException ex) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.CONFLICT.value(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }
    @ExceptionHandler(NoSuchElementException.class)
    public final ResponseEntity<ErrorResponse> handleNoSuchElementException(NoSuchElementException ex) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }
   @ExceptionHandler(Exception.class)
    public final ResponseEntity<ErrorResponse> handleGenericException(Exception ex) {
        System.out.println("autsch");
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Internal Server Error");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }


}
