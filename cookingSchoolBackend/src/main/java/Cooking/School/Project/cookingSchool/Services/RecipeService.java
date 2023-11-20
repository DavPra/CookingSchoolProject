package Cooking.School.Project.cookingSchool.Services;

import Cooking.School.Project.cookingSchool.entities.Recipe;
import Cooking.School.Project.cookingSchool.repository.RecipesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class RecipeService {

    @Autowired
    RecipesRepository recipesRepository;

    public RecipeService(){
    }

    public Recipe addRecipe(Recipe recipe){
        recipesRepository.save(recipe);
        return recipe;
    }

    public Recipe getRecipeById(Long id){
        return recipesRepository.findById(id).get();
    }

    public void deleteRecipeById(Long id){
        recipesRepository.deleteById(id);
    }

    public Recipe updateRecipe(Recipe recipe){
        recipesRepository.save(recipe);
        return recipe;
    }
}
