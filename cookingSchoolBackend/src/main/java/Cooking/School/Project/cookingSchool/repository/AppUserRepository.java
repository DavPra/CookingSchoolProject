package Cooking.School.Project.cookingSchool.repository;

import Cooking.School.Project.cookingSchool.entities.AppUser;
import Cooking.School.Project.cookingSchool.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AppUserRepository extends JpaRepository <AppUser, Long> {

    @Query("SELECT appUser FROM AppUser appUser where appUser.username = :username")
    public AppUser findByUsername(String username);
}
