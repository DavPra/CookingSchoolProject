package Cooking.School.Project.cookingSchool.controller;

import Cooking.School.Project.cookingSchool.Services.RecipeService;
import Cooking.School.Project.cookingSchool.Services.TagService;
import Cooking.School.Project.cookingSchool.entities.Recipe;
import Cooking.School.Project.cookingSchool.restapi.dto.RecipeCourse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class RecipeController {

    private static final Logger logger = LoggerFactory.getLogger(RecipeService.class);

    @Autowired
    private TagService tagService;

    @Autowired
    private RecipeService recipeService;

    @PostMapping("/admin/addRecipe")
    public ResponseEntity<?> addRecipeToCourse(@RequestBody RecipeCourse recipeCourse) {
        System.out.println("Hallo-controller");
        RecipeCourse response = recipeService.addRecipeToCourse(recipeCourse);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @GetMapping("admin/getAllRecipes")
    public ResponseEntity<List<Recipe>> getAllRecipes() {
        List<Recipe> recipes = recipeService.getAllRecipe();
        return new ResponseEntity<>(recipes, HttpStatus.OK);
    }

    @GetMapping("/admin/recipe/{recipeId}")
    public ResponseEntity<?> getRecipeById(@PathVariable Long recipeId) {
            Recipe recipe = recipeService.getRecipeById(recipeId);
            return new ResponseEntity<>(recipe, HttpStatus.OK);
    }


    @Transactional
    @PutMapping("/admin/updateRecipe/{recipeId}")
    public ResponseEntity<?> updateRecipe(
            @PathVariable Long recipeId,
            @RequestBody Recipe updatedRecipe
    ) {
        Recipe updated = recipeService.updateRecipe(recipeId, updatedRecipe);
        logger.info("Updated Ingredients: " + updated.getIngredients());
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("admin/recipe/{recipeId}")
    public ResponseEntity<?> deleteRecipeById(@PathVariable Long recipeId) {
        logger.info("get id" + recipeId);
            recipeService.deleteRecipeById(recipeId);
            return new ResponseEntity<>("Recipe erfolgreich gelöscht", HttpStatus.OK);
    }

    @GetMapping("/users/recipes/{userId}")
    public ResponseEntity<Set<Recipe>> getUserRecipes(@PathVariable Long userId){
        Set<Recipe> recipes = recipeService.getUserRecipes(userId);
        return ResponseEntity.ok(recipes);
    }

    //TODO Soll der user auch im Pfad sein?
    @GetMapping("/recipes/{recipeId}")
    public ResponseEntity<?> getUserRecipesDetails(@PathVariable  Long recipeId){
        Recipe recipe = recipeService.getRecipeById(recipeId);
        return ResponseEntity.ok(recipe);
    }

}
