package Cooking.School.Project.cookingSchool.Services;

import Cooking.School.Project.cookingSchool.entities.*;
import Cooking.School.Project.cookingSchool.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class SetUpService {

    private final CoursesRepository coursesRepository;
    private final IngredientRepository ingredientRepository;
    private final RecipeRepository recipeRepository;
    private final UserRepository userRepository;

    @Autowired
    public SetUpService(CoursesRepository coursesRepository, IngredientRepository ingredientRepository, RecipeRepository recipeRepository, UserRepository userRepository) {
        this.coursesRepository = coursesRepository;
        this.ingredientRepository = ingredientRepository;
        this.recipeRepository = recipeRepository;
        this.userRepository = userRepository;
    }

    @PostConstruct
    public void setUp() {
        Course course = new Course();
        course.setTitle("Kurs 1");
        course.setDescription("Beschreibung 1");
        course.setTeacher("Lehrer 1");
        coursesRepository.save(course);
        System.out.println("Kurs 1 wurde erstellt");

        Ingredient ingredient = new Ingredient();
        ingredient.setTitle("Zutat 1");
        ingredient.setUnit("Einheit 1");
        ingredientRepository.save(ingredient);

        Recipe recipe = new Recipe();
        recipe.setTitle("Rezept 1");
        recipe.setDescription("Beschreibung 1");
        recipe.setPreparation(120);
        recipe.setDifficulty(3);
        recipeRepository.save(recipe);

        User user = new User();
        user.setFirstname("Vorname 1");
        user.setLastname("Nachname 1");
        user.setEmail("Email 1");
        user.setPassword("Passwort 1");
        user.setAdmin(false);
        userRepository.save(user);
    }
}
