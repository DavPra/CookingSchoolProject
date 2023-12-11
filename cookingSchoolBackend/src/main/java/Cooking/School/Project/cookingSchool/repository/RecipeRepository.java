package Cooking.School.Project.cookingSchool.repository;

import Cooking.School.Project.cookingSchool.entities.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {

    boolean existsByTitle(String title);

    @Query("select r from Recipe r " +
            "inner join r.courses c " +
            "inner join c.users u " +
            "where u.userId = :userId")
    List<Recipe> findRecipesByUser(@Param("userId") Long userId);
}
