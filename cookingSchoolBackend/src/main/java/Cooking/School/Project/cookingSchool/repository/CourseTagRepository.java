package Cooking.School.Project.cookingSchool.repository;

import Cooking.School.Project.cookingSchool.entities.CourseTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CourseTagRepository extends JpaRepository<CourseTag, Long> {

    @Query("SELECT ct FROM CourseTag ct WHERE ct.courseTagTitle = :title")
    Optional<CourseTag> findByTitle(@Param("title") String title);


}
