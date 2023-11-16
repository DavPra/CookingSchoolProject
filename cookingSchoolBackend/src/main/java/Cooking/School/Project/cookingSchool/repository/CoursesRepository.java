package Cooking.School.Project.cookingSchool.repository;

import Cooking.School.Project.cookingSchool.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoursesRepository extends JpaRepository<Course, Long> {

}
