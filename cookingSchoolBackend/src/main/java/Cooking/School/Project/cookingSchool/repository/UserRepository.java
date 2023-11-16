package Cooking.School.Project.cookingSchool.repository;

import Cooking.School.Project.cookingSchool.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
