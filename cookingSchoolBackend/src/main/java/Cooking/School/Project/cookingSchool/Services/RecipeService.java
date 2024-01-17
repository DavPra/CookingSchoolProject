package Cooking.School.Project.cookingSchool.Services;

import Cooking.School.Project.cookingSchool.entities.Course;
import Cooking.School.Project.cookingSchool.entities.Ingredient;
import Cooking.School.Project.cookingSchool.entities.Recipe;
import Cooking.School.Project.cookingSchool.entities.User;
import Cooking.School.Project.cookingSchool.exceptions.*;
import Cooking.School.Project.cookingSchool.repository.CourseRepository;
import Cooking.School.Project.cookingSchool.repository.IngredientRepository;
import Cooking.School.Project.cookingSchool.repository.RecipeRepository;
import Cooking.School.Project.cookingSchool.repository.UserRepository;
import Cooking.School.Project.cookingSchool.restapi.dto.RecipeCourse;
import Cooking.School.Project.cookingSchool.restapi.dto.RecipeDto;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    UserRepository userRepository;


    public RecipeService() {

    }

/* Martin meint wir müssen Ingredient nicht überprüfen, einfach nur speichern */

    @Transactional
    public RecipeCourse addRecipeToCourse(RecipeCourse recipeCourse) {
        Set<Course> courses = loadCourses(recipeCourse.getCourseIds());
        Set<Ingredient> ingredients = loadIngredients(recipeCourse.getIngredients());

        Recipe recipe = new Recipe();
        recipe.setTitle(recipeCourse.getTitle());
        recipe.setDescription(recipeCourse.getDescription());
        recipe.setDifficulty(recipeCourse.getDifficulty());
        recipe.setPreparation(recipeCourse.getPreparation());

        recipe.setIngredients(ingredients);
        recipe = recipeRepository.save(recipe);

        recipe.setCourses(courses);


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
            /*
            if (ingredient.getIngredientId() == null) {
                ingredientRepository.save(ingredient);
            }
             */

            Optional<Ingredient> loadedIngredientOptional = ingredientRepository.findBy(
                    ingredient.getTitle(),
                    ingredient.getUnit(),
                    ingredient.getQuantity());

            if(!loadedIngredientOptional.isPresent()) {
                ingredient = ingredientRepository.save(ingredient);
            } else {
                ingredient = loadedIngredientOptional.get();
            }



            /*
            Ingredient loadedIngredient = loadedIngredientOptional.orElseThrow(() ->
                    new IngredientNotFoundException("Ingredient not found"));*/

            loadedIngredients.add(ingredient);
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
        Recipe recipe = recipeRepository.findById(recipeId)
                .orElseThrow(() -> new RecipeNotFoundException("Recipe not found with " + recipeId));

        return recipe;
    }

    /**
     * updatet Recipe und zutaten anhand der recipeId im pfad und findet Ingredient anhand der id,checkt ob da und updatet
     *
     * @param recipeId //* @param updatedRecipe
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


    /**
     * Deletes recipe including associated ingredients using the recipe ID
     *
     * @param recipeId
     * @throws PrimaryIdNullOrEmptyException
     */
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

    @Transactional

    public Set<Recipe> getUserRecipes(Long userId) throws UserNotFoundException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User with Id  " + userId + " not found"));

        Set<Course> userCourses = user.getCourses();
        Set<Recipe> userRecipes = new HashSet<>();

        for( Course course : userCourses){
            Set<Recipe> recipesFromCourse = course. getRecipes();
            if (recipesFromCourse != null ){
                userRecipes.addAll(recipesFromCourse);
            }
        }

        return userRecipes;
    }
}



