package Cooking.School.Project.cookingSchool.Services;

import Cooking.School.Project.cookingSchool.entities.Recipe;
import Cooking.School.Project.cookingSchool.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

@Service
public class RecipeService {

    @Autowired
    RecipeRepository recipeRepository;

    public RecipeService() {

    }

    public Recipe addRecipe(Recipe recipe){
        if(recipeRepository.existsByTitle(recipe.getTitle())){
            throw new DuplicateKeyException("Dieser Rezept Title exestiert bereits");
        }
        recipeRepository.save(recipe);
        return recipe;

    }
}
