package Cooking.School.Project.cookingSchool;

import Cooking.School.Project.cookingSchool.config.HSQLDBStarter;
import Cooking.School.Project.cookingSchool.entities.Course;
import Cooking.School.Project.cookingSchool.entities.Ingredient;
import Cooking.School.Project.cookingSchool.entities.Recipe;
import Cooking.School.Project.cookingSchool.entities.User;
import Cooking.School.Project.cookingSchool.repository.CourseRepository;
import Cooking.School.Project.cookingSchool.repository.IngredientRepository;
import Cooking.School.Project.cookingSchool.repository.RecipeRepository;
import Cooking.School.Project.cookingSchool.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

@SpringBootApplication(scanBasePackages = "Cooking.School.Project.cookingSchool")
@RequiredArgsConstructor
public class Main implements CommandLineRunner {

	private final CourseRepository courseRepository;
	private final IngredientRepository ingredientRepository;
	private final RecipeRepository recipeRepository;
	private final UserRepository userRepository;
	private final HSQLDBStarter hsqldbStarter;

	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String hbm2ddl;

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		if(hbm2ddl != null && hbm2ddl.contains("create")){
			setUp();
		}
	}

	public void setUp() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");


		Course course = new Course();
		course.setCourseTitle("Kurs 1");
		course.setDescription("Beschreibung 1");
		course.setTeacher("Lehrer 1");
		course.setMaxAttendants(10);
		course.setPrice(100);
		course.setStartDate(LocalDateTime.parse("2023-11-20T14:30:00", formatter));
		courseRepository.save(course);
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

		Set<Ingredient> ingredients = new HashSet<>();
		Ingredient ingredientForRecipe = new Ingredient();
		ingredient.setTitle("Zutat 1");
		ingredient.setUnit("Einheit 1");
		ingredientRepository.save(ingredient);
		ingredients.add(ingredient);
		recipe.setIngredients(ingredients);
		recipeRepository.save(recipe);

		User user = new User();
		user.setFirstname("admin 1");
		user.setLastname("Nachname 1");
		user.setEmail("Email 1");
		user.setPassword("Passwort 1");
		user.setAdmin(true);
		userRepository.save(user);
	}
}
