package Cooking.School.Project.cookingSchool.repository;

import Cooking.School.Project.cookingSchool.entities.User;
import Cooking.School.Project.cookingSchool.security.AUTHORITIES;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface AppUserRepository extends JpaRepository <User, Long> {

    @Query("SELECT appUser FROM User appUser where appUser.username = :username")
    User findByUsername(String username);
    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.authorities = :authorities WHERE u.userId = :userId")
    void updateAuthorities(@Param("userId") Long userId, @Param("authorities") List<AUTHORITIES> authorities);

}
