package Cooking.School.Project.cookingSchool.repository;

import Cooking.School.Project.cookingSchool.entities.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {
}
