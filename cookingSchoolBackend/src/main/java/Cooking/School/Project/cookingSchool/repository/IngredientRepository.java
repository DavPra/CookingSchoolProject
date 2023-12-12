package Cooking.School.Project.cookingSchool.repository;

import Cooking.School.Project.cookingSchool.entities.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {


    @Query("select i from Ingredient i " +
            "where lower(i.title) = lower(:title) " +
            "and i.unit = :unit " +
            "and i.quantity = :quantity")
    public Optional<Ingredient> findBy(@Param("title") String title,
                                       @Param("unit") String unit,
                                       @Param("quantity") int quantity);



}
