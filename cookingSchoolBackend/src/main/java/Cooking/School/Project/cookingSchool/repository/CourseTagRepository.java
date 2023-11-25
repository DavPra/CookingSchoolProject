package Cooking.School.Project.cookingSchool.repository;

import Cooking.School.Project.cookingSchool.entities.CourseTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseTagRepository extends JpaRepository<CourseTag, Long> {
}
