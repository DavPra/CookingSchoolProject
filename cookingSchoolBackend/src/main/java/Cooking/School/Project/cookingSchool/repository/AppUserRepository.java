package Cooking.School.Project.cookingSchool.repository;

import Cooking.School.Project.cookingSchool.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AppUserRepository extends JpaRepository <User, Long> {

    @Query("SELECT appUser FROM User appUser where appUser.username = :username")
    User findByUsername(String username);
}
