package Cooking.School.Project.cookingSchool.controller;

import Cooking.School.Project.cookingSchool.Services.RecipeService;
import Cooking.School.Project.cookingSchool.Services.TagService;
import Cooking.School.Project.cookingSchool.entities.Recipe;
import Cooking.School.Project.cookingSchool.exceptions.CourseNotFoundException;
import Cooking.School.Project.cookingSchool.exceptions.PrimaryIdNullOrEmptyException;
import Cooking.School.Project.cookingSchool.exceptions.RecipeNotFoundException;
import Cooking.School.Project.cookingSchool.restapi.DTO.RecipeCourse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
}
