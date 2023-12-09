package Cooking.School.Project.cookingSchool.controller;

import Cooking.School.Project.cookingSchool.Services.RecipeService;
import Cooking.School.Project.cookingSchool.Services.TagService;
import Cooking.School.Project.cookingSchool.entities.Recipe;
import Cooking.School.Project.cookingSchool.exceptions.CourseNotFoundException;
import Cooking.School.Project.cookingSchool.exceptions.PrimaryIdNullOrEmptyException;
import Cooking.School.Project.cookingSchool.exceptions.RecipeNotFoundException;
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

    //--------------------------- Recipe

    @Transactional
    @PostMapping("/admin/addRecipe/{courseId}")
    public ResponseEntity<?> addRecipeToCourse(@PathVariable Long courseId, @RequestBody Recipe recipe) {
        try {
            recipeService.addRecipeToCourse(courseId, recipe);
            return new ResponseEntity<>("Rezept erfolgreich dem Kurs hinzugefügt", HttpStatus.OK);
        } catch (CourseNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("admin/getAllRecipes")
    public ResponseEntity<List<Recipe>> getAllRecipes(){
        List<Recipe> recipes = recipeService.getAllRecipe();
        return new ResponseEntity<>(recipes, HttpStatus.OK);
    }

    @GetMapping("/admin/recipe/{id}")
    public ResponseEntity<?> getRecipeById(@PathVariable Long recipeId) {
        try {
            Recipe recipe = recipeService.getRecipeById(recipeId);
            return new ResponseEntity<>(recipe, HttpStatus.OK);
        } catch (PrimaryIdNullOrEmptyException pinoee) {
            return new ResponseEntity<>(pinoee.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    @Transactional
    @PutMapping("/admin/updateRecipe/{recipeId}")
    public ResponseEntity<?> updateRecipe(
           @PathVariable Long recipeId,
           @RequestBody Recipe updatedRecipe
           ){
        try {
            Recipe updated = recipeService.updateRecipe(recipeId, updatedRecipe);
            logger.info("Updated Ingredients: " + updated.getIngredients());

            return  new ResponseEntity<>(updated, HttpStatus.OK);
        }catch (RecipeNotFoundException rnfe){
            return new ResponseEntity<>("Recipe not found", HttpStatus.NOT_FOUND);
        }

   }




    @DeleteMapping("admin/recipe/{recipeId}")
    public ResponseEntity<?> deleteRecipeById(@PathVariable Long recipeId){
        logger.info("get id"+ recipeId);
        try{
            recipeService.deleteRecipeById(recipeId);
            return new ResponseEntity<>("Recipe erfolgreich gelöscht", HttpStatus.OK);
        } catch (PrimaryIdNullOrEmptyException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }catch (RecipeNotFoundException rnfe){
            return new ResponseEntity<>(rnfe.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }



}
