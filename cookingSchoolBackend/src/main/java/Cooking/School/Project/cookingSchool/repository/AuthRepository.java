package Cooking.School.Project.cookingSchool.repository;

import Cooking.School.Project.cookingSchool.entities.GrantedAuthorityImpl;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthRepository extends JpaRepository<GrantedAuthorityImpl, Long> {
}
