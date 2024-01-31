package Cooking.School.Project.cookingSchool.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "ingredient not found")
public class IngredientNotFoundException extends RuntimeException {

    private Long ingredientId;

    public IngredientNotFoundException(Long ingredientId) {
        super("Ingredient not found with Id" + ingredientId);
        this.ingredientId = ingredientId;
    }
    public IngredientNotFoundException(String message){
        super(message);
    }

    public Long getIngredientId(){
        return ingredientId;
    }
}
