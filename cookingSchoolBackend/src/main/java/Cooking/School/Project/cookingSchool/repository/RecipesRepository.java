package Cooking.School.Project.cookingSchool.repository;

import Cooking.School.Project.cookingSchool.entities.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipesRepository extends JpaRepository<Recipe, Long> {
}
