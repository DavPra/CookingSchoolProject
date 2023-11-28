package Cooking.School.Project.cookingSchool.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "recipe not found")
public class RecipeNotFoundException extends RuntimeException{

    private Long recipeId;

        public RecipeNotFoundException(Long userId) {
            super("User not found with Id" + userId);
            this.recipeId = recipeId;
        }

        public RecipeNotFoundException(String message){
            super(message);
        }


        public Long getUserId(){
            return recipeId;
        }
}
