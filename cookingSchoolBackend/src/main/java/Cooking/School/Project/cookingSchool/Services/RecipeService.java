package Cooking.School.Project.cookingSchool.Services;

import Cooking.School.Project.cookingSchool.entities.Course;
import Cooking.School.Project.cookingSchool.entities.Ingredient;
import Cooking.School.Project.cookingSchool.entities.Recipe;
import Cooking.School.Project.cookingSchool.exceptions.CourseNotFoundException;
import Cooking.School.Project.cookingSchool.exceptions.IngredientNotFoundException;
import Cooking.School.Project.cookingSchool.exceptions.PrimaryIdNullOrEmptyException;
import Cooking.School.Project.cookingSchool.exceptions.RecipeNotFoundException;
import Cooking.School.Project.cookingSchool.repository.CourseRepository;
import Cooking.School.Project.cookingSchool.repository.IngredientRepository;
import Cooking.School.Project.cookingSchool.repository.RecipeRepository;
import Cooking.School.Project.cookingSchool.restapi.DTO.RecipeCourse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class RecipeService {

    @Autowired
    RecipeRepository recipeRepository;

    @Autowired
    IngredientRepository ingredientRepository;

    @Autowired
    CourseRepository courseRepository;



    public RecipeService() {

    }

    @Transactional
    public RecipeCourse addRecipeToCourse(RecipeCourse recipeCourse) {
        Set<Course> courses = loadCourses(recipeCourse.getCourseIds());
        Set<Ingredient> ingredients = loadIngredients(recipeCourse.getIngredients());
        Recipe recipe = new Recipe();
        recipe.setTitle(recipeCourse.getTitle());
        recipe.setDescription(recipe.getDescription());
        recipe.setDifficulty(recipe.getDifficulty());
        recipe.setPreparation(recipe.getPreparation());

        recipe.setCourses(courses);
        recipe.setIngredients(ingredients);

        recipe = recipeRepository.save(recipe);
        recipeCourse.setRecipeId(recipe.getRecipeId());


        return recipeCourse;
    }

    private Set<Course> loadCourses(Set<Long> courseIds) {
        Set<Course> courses = new HashSet<>();
        for (Long courseId : courseIds) {
            Course course = courseRepository.findById(courseId)
                    .orElseThrow(() -> new CourseNotFoundException("Course not found with ID: " + courseId));
            courses.add(course);
        }
        return courses;
    }

    private Set<Ingredient> loadIngredients(Set<Ingredient> ingredients) {
        Set<Ingredient> loadedIngredients = new HashSet<>();

        for (Ingredient ingredient : ingredients) {
            if (ingredient.getIngredientId() == null) {
                ingredientRepository.save(ingredient);
            }

            Optional<Ingredient> loadedIngredientOptional = ingredientRepository.findBy(
                    ingredient.getTitle(),
                    ingredient.getUnit(),
                    ingredient.getQuantity());

            Ingredient loadedIngredient = loadedIngredientOptional.orElseThrow(() ->
                    new IngredientNotFoundException("Ingredient not found"));

            loadedIngredients.add(loadedIngredient);
        }

        return loadedIngredients;
    }






    public List<Recipe> getAllRecipe() throws RecipeNotFoundException {

        return recipeRepository.findAll();

    }


    public Recipe getRecipeById(Long recipeId) throws PrimaryIdNullOrEmptyException {
        if (recipeId == null || recipeId <= 0) {
            throw new PrimaryIdNullOrEmptyException("Id is null or empty");
        }
        return recipeRepository.findById(recipeId).get();
    }

    //TODO gibt zutaten nicht in der response aus

    /**
     * updatet Recipe und zutaten anhand der recipeId im pfad und findet Ingredient anhand der id,checkt ob da und updatet
     *
     * @param recipeId
     //* @param updatedRecipe
     * @return
     * @throws RecipeNotFoundException
     * @throws PrimaryIdNullOrEmptyException
     */

    @Transactional
    public Recipe updateRecipe(Long recipeId, Recipe updatedRecipe) throws PrimaryIdNullOrEmptyException {
        Recipe existingRecipe = recipeRepository.findById(recipeId)
                .orElseThrow(() -> new RecipeNotFoundException("Recipe with Id  " + recipeId + " not found"));

        existingRecipe.setTitle(updatedRecipe.getTitle());
        existingRecipe.setDescription(updatedRecipe.getDescription());
        existingRecipe.setDifficulty(updatedRecipe.getDifficulty());
        existingRecipe.setPreparation(updatedRecipe.getPreparation());

        //TODO ingredient besser per title finden?
      if (updatedRecipe.getIngredients() != null) {
            existingRecipe.getIngredients().forEach(ingredient -> {
                Ingredient updatedIngredient = ingredientRepository.findById(ingredient.getIngredientId())
                        .orElseThrow(() -> new IngredientNotFoundException("Ingredient with Id " + ingredient.getIngredientId() + " notfound"));


                ingredient.setTitle(updatedIngredient.getTitle());
                ingredient.setUnit(updatedIngredient.getUnit());
                ingredient.setQuantity(updatedIngredient.getQuantity());


            });
        }

        return recipeRepository.save(existingRecipe);
    }


    //TODO Ingredients löschen? NEIN NICHT Löschen nicht cascadieren, Ingredients Servic mit delete vorher checken ob ingredient nicht verwendet wird
    @Transactional
    public void deleteRecipeById(Long recipeId) throws PrimaryIdNullOrEmptyException {
        if (recipeId == null || recipeId <= 0) {
            throw new PrimaryIdNullOrEmptyException("Id is null or empty");
        }

        Recipe recipe = recipeRepository.findById(recipeId)
                .orElseThrow(() -> new RecipeNotFoundException("Recipe with Id " + recipeId + " not found"));

        recipe.getIngredients().forEach(ingredient -> ingredientRepository.deleteById(ingredient.getIngredientId()));

        recipeRepository.deleteById(recipeId);
    }
}



