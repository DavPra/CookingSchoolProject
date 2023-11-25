package Cooking.School.Project.cookingSchool.repository;

import Cooking.School.Project.cookingSchool.entities.RecipeTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeTagRepository extends JpaRepository<RecipeTag, Long> {
}
